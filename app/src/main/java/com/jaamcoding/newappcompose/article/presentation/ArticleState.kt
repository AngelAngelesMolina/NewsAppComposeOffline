package com.jaamcoding.newappcompose.article.presentation

import com.jaamcoding.newappcompose.core.domain.Article

data class ArticleState(
    val article: Article? = null,
    val isLoading: Boolean = false,
    val isError: Boolean? = false
)
