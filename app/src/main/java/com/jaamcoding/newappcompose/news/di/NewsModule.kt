package com.jaamcoding.newappcompose.news.di

import com.jaamcoding.newappcompose.news.presentation.NewsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    viewModel { NewsViewModel(get()) }

}