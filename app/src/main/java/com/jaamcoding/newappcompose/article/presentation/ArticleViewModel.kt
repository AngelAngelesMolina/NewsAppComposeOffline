package com.jaamcoding.newappcompose.article.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaamcoding.newappcompose.core.domain.NewsRepository
import com.jaamcoding.newappcompose.core.domain.NewsResult
import kotlinx.coroutines.launch

class ArticleViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var state by mutableStateOf(ArticleState())
        private set

    fun onAction(action: ArticleAction) {
        when (action) {
            is ArticleAction.LoadArticle -> {
                state = state.copy(isLoading = true)
                loadArticle(action.articleId)
            }
        }
    }

    private fun loadArticle(articleId: String) {

        if (articleId.isBlank()) {
            state = state.copy(isError = true)
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            newsRepository.getArticle(articleId).collect { articleResult ->
                state = when (articleResult) {
                    is NewsResult.Error -> {
                        state.copy(isError = true)
                    }

                    is NewsResult.Success -> {
                        state.copy(
                            isError = false,
                            article = articleResult.data
                        )

                    }
                }
            }

            state = state.copy(isLoading = false)

        }


    }

}