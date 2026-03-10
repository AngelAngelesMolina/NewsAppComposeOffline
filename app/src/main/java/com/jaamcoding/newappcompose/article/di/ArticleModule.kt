package com.jaamcoding.newappcompose.article.di

import com.jaamcoding.newappcompose.article.presentation.ArticleViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel


val articleModule = module {
    viewModel { ArticleViewModel(get()) }
}