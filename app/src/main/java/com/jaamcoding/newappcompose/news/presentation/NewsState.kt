package com.jaamcoding.newappcompose.news.presentation

import com.jaamcoding.newappcompose.core.domain.Article

data class NewsState(
    val articleList: List<Article> = emptyList(),
    val nextPage: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,

)