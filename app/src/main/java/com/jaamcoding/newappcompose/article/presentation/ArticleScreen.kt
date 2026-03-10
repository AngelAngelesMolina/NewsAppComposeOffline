package com.jaamcoding.newappcompose.article.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jaamcoding.newappcompose.article.presentation.components.ArticleDetails
import com.jaamcoding.newappcompose.core.domain.Article
import com.jaamcoding.newappcompose.core.presentation.ui.theme.NewAppComposeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleScreenCore(
    viewModel: ArticleViewModel = koinViewModel(),
    articleId: String
) {
    LaunchedEffect(true) { //This will trigger onl once
        viewModel.onAction(ArticleAction.LoadArticle(articleId))

    }
    ArticleScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )

}


@Composable
fun ArticleScreen(
    state: ArticleState,
    onAction: (ArticleAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {

            if (state.isLoading && state.article == null) {
                CircularProgressIndicator()
            }
            if (state.isError && state.article == null) {
                Text(
                    text = "Can't load article. Try again later.",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }

            state.article?.let { article ->

                ArticleDetails(article = article)

            }

        }

    }
}




@Preview(showBackground = true)
@Composable
private fun ArticleScreenPrev() {
    NewAppComposeTheme(
    ) {
        ArticleScreen(
            state = ArticleState(),
            onAction = {}
        )
    }
}