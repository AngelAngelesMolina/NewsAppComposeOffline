package com.jaamcoding.newappcompose.news.presentation

import com.jaamcoding.newappcompose.core.domain.Article
import com.jaamcoding.newappcompose.core.domain.NewsList

data class NewsState(
    val newsList: List<Article> = emptyList(),
    val nextPage: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,

)