package com.jaamcoding.newappcompose.core.domain


data class NewsList(
    val nextPage: String?,
    val articles: List<Article>
)
