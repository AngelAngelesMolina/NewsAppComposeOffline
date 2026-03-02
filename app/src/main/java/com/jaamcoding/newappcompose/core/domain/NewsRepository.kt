package com.jaamcoding.newappcompose.core.domain

import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews() : Flow<NewsResult<NewsList>>

}