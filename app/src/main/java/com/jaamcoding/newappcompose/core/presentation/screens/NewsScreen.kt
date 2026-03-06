package com.jaamcoding.newappcompose.core.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaamcoding.newappcompose.core.presentation.components.ArticleItem
import com.jaamcoding.newappcompose.core.presentation.ui.theme.NewAppComposeTheme
import com.jaamcoding.newappcompose.news.presentation.NewsAction
import com.jaamcoding.newappcompose.news.presentation.NewsState
import com.jaamcoding.newappcompose.news.presentation.NewsViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreenCore(
    viewModel: NewsViewModel = koinViewModel(),
    onArticleClick: (String) -> Unit
) {
    NewsScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onArticleClick = onArticleClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    state: NewsState,
    onAction: (NewsAction) -> Unit,
    onArticleClick: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = "The News",
                        fontSize = 33.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                windowInsets = WindowInsets(
                    top = 50.dp, bottom = 8.dp
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading && state.articleList.isEmpty()) {
                CircularProgressIndicator()
            }
            if (state.isError && state.articleList.isEmpty()) {
                Text(
                    text = "Can't load news",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            if (state.articleList.isNotEmpty()) {
                val listState = rememberLazyListState()

                val shouldPaginate = remember {
                    derivedStateOf {
                        val totalItems = listState.layoutInfo.totalItemsCount
                        val lastVisibleIndex =
                            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                        lastVisibleIndex == totalItems - 1 && !state.isLoading //we should paginate
                    }
                }
                LaunchedEffect(listState) {
                    snapshotFlow { shouldPaginate.value }
                        .distinctUntilChanged()
                        .filter { it }
                        .collect { onAction(NewsAction.Paginate) }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        bottom = 8.dp,
                    ),
                    state = listState
                ) {
                    itemsIndexed(
                        items = state.articleList,
                        key = { _, article -> article.articleId }
                    )
                    { index, article ->

                        ArticleItem(
                            article = article,
                            onArticleClick = onArticleClick

                        )

                    }

                }

            }

        }

    }
}


@Preview(showBackground = true)
@Composable
private fun NewsScreenPrev() {
    NewAppComposeTheme() {
        NewsScreen(
            state = NewsState(),
            onAction = {},
            onArticleClick = {}
        )
    }
}