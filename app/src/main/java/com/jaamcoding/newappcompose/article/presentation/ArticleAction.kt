package com.jaamcoding.newappcompose.article.presentation

sealed interface ArticleAction {
    data class LoadArticle(val articleId : String): ArticleAction
}
