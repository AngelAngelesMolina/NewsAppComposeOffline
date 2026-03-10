package com.jaamcoding.newappcompose.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jaamcoding.newappcompose.article.presentation.ArticleScreenCore
import com.jaamcoding.newappcompose.core.presentation.Screen
import com.jaamcoding.newappcompose.core.presentation.screens.NewsScreenCore

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.News
    ) {
        composable<Screen.News> {
            NewsScreenCore() {
                navController.navigate(Screen.Article(it))
            }

        }
        composable<Screen.Article> { backStackEntry ->
            val article: Screen.Article = backStackEntry.toRoute()
            ArticleScreenCore(articleId = article.articleId)

        }
    }

}