package com.jaamcoding.newappcompose.article.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jaamcoding.newappcompose.core.domain.Article
import com.jaamcoding.newappcompose.core.presentation.ui.theme.NewAppComposeTheme

@Composable
fun ArticleDetails(modifier: Modifier = Modifier, article: Article) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {

        Text(
            text = article.sourceName,
            fontSize = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.pubDate,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.title,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model =
                article.imageUrl,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary.copy(0.4f))
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.content,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }

}


@Preview(showBackground = true)
@Composable
private fun ArticleDetailsPrev() {
    NewAppComposeTheme(
    ) {
        val article = Article(
            articleId = "art-001",
            title = "Kotlin Coroutines in Android",
            description = "Coroutines simplify asynchronous work in Android apps.",
            content = "Kotlin Coroutines allow developers to write asynchronous code in a simple and readable way. They help manage background tasks like network calls and database operations without blocking the main thread.",
            pubDate = "2026-03-09",
            sourceName = "Android Dev Weekly",
            imageUrl = "https://example.com/images/coroutines.jpg"
        )
        ArticleDetails(article = article)

    }
}