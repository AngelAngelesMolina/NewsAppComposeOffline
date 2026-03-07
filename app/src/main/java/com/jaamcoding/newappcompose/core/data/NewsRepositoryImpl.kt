package com.jaamcoding.newappcompose.core.data

import com.jaamcoding.newappcompose.core.data.local.ArticlesDao
import com.jaamcoding.newappcompose.core.data.remote.NewsListDto
import com.jaamcoding.newappcompose.core.data.remote.toArticle
import com.jaamcoding.newappcompose.core.data.remote.toArticleEntity
import com.jaamcoding.newappcompose.core.data.remote.toNewsList
import com.jaamcoding.newappcompose.core.domain.Article
import com.jaamcoding.newappcompose.core.domain.NewsList
import com.jaamcoding.newappcompose.core.domain.NewsRepository
import com.jaamcoding.newappcompose.core.domain.NewsResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.cancellation.CancellationException

class NewsRepositoryImpl(
    private val httpClient: HttpClient,
    private val dao: ArticlesDao
) : NewsRepository {

    private val tag = "NewsRepositoryImpl: "
    private val newsApi = "https://newsdata.io/api/1/latest"
    private val apiKey = "pub_ea0950a215d941158db618ffa44fa178"

    private suspend fun getLocalNews(nextPage: String?): NewsList {
        val localNews = dao.getArticleList()
        println(tag + "getLocalNews: ${localNews.size} nextPage: $nextPage")

        val newsList = NewsList(
            articles = localNews.map { it.toArticle() },
            nextPage = nextPage
        )
        return newsList
    }

    private suspend fun getRemoteNews(nextPage: String?): NewsList {
        val newsListDto: NewsListDto = httpClient.get(newsApi) {
            parameter("apikey", apiKey)
            parameter("language", "en")
            if (nextPage != null) parameter("page", nextPage)
        }.body()
        println(tag + "getRemoteNews: ${newsListDto.results.size} nextPage: $nextPage")

        return newsListDto.toNewsList()

    }


    override suspend fun getNews(): Flow<NewsResult<NewsList>> {
        return flow {
            val remoteNewsList = try {
                getRemoteNews(null)
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e
                println(tag + "getNews remote exception: ${e.message}")
                null
            }
            remoteNewsList?.let {
                dao.clearDatabase()
                dao.upsertArticleList(remoteNewsList.articles.map { it.toArticleEntity() })
                emit(NewsResult.Success(getLocalNews(remoteNewsList.nextPage)))
                return@flow
            }
            //We don't have data in the database or internet
            val localNewsList = getLocalNews(null)
            if (localNewsList.articles.isNotEmpty()) {
                emit(NewsResult.Success(localNewsList))
                return@flow
            }
            emit(NewsResult.Error("Something went wrong"))

        }
    }

    override suspend fun paginate(nextPage: String?): Flow<NewsResult<NewsList>> {
        return flow {
            val remoteNewsList = try {
                getRemoteNews(nextPage)
            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e
                println(tag + "getNews remote exception: ${e.message}")
                null
            }
            remoteNewsList?.let {
                dao.upsertArticleList(remoteNewsList.articles.map { it.toArticleEntity() })
                //Not getting them from the database like getNEws
                //because we will also get old items that we already have before paginating
                emit(NewsResult.Success(remoteNewsList))
                return@flow
            }
        }

    }

    override suspend fun getArticle(articleId: String): Flow<NewsResult<Article>> {
        return flow {
            dao.getArticleById(articleId)?.let { articleId ->
                println(tag + "getArticle local: ${articleId.title}")
                emit(NewsResult.Success(articleId.toArticle()))
                return@flow
            }

            try {

                val remoteArticle = httpClient.get(newsApi){
                    parameter("apikey", apiKey)
                    parameter("id", articleId)
                }.body()

            } catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e
                println(tag + "getArticle remote exception: ${e.message}")
                emit(NewsResult.Error("Something went wrong"))
            }


        }

    }

}