package com.jaamcoding.newappcompose.news.presentation

sealed interface NewsAction {
    data object Paginate : NewsAction

}
