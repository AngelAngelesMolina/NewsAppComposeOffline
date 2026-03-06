package com.jaamcoding.newappcompose.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleClick: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { onArticleClick(article.articleId) }
            .padding(vertical = 16.dp)
    ) {

        Text(
            text = article.sourceName,
            fontSize = 22.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.title,
            fontSize = 18.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = article.imageUrl,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(MaterialTheme.colorScheme.primary.copy(0.3f))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.description,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.pubDate,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(MaterialTheme.colorScheme.primaryContainer.copy(0.7f))
    )


}

@Preview(showBackground = true)
@Composable
private fun ArticleItemPrev() {
    NewAppComposeTheme {
        ArticleItem(
            modifier = Modifier,
            article = Article(
                articleId = "1",
                title = "title",
                sourceName = "source",
                imageUrl = "proof",
                description = "Proof description",
                pubDate = "date",
                content = "content"
            )
        ) {

        }

    }
}