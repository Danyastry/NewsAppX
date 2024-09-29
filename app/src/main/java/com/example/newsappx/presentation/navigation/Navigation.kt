package com.example.newsappx.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsappx.presentation.bookmark.BookmarkScreen
import com.example.newsappx.presentation.bookmark.BookmarkViewModel
import com.example.newsappx.presentation.bottomBar.BottomNavigationBar
import com.example.newsappx.presentation.details.DetailsScreen
import com.example.newsappx.presentation.main.MainScreen
import com.example.newsappx.presentation.onBoarding.component.OnBoardingScreen
import com.example.newsappx.ui.theme.Background
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val hideBottomBar = listOf(RouteNav.DETAILS_SCREEN, RouteNav.APP_START_NAVIGATION)

    Scaffold(
        bottomBar = {
            if (currentRoute !in hideBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = RouteNav.APP_START_NAVIGATION,
            modifier = Modifier
                .padding(paddingValues)
                .background(Background)
        ) {
            composable(route = RouteNav.APP_START_NAVIGATION) {
                OnBoardingScreen(navController = navController)
            }

            composable(route = RouteNav.NEWS_NAVIGATION) {
                MainScreen(navController = navController)
            }
            composable(route = RouteNav.DETAILS_SCREEN) {
                val articleJson = it.arguments?.getString(RouteNav.ARTICLE_JSON)
                val article = Route.getArticleFromGson(articleJson!!)
                DetailsScreen(article = article)
            }
            composable(route = RouteNav.BOOKMARK) {
                val viewModel: BookmarkViewModel = koinViewModel()
                BookmarkScreen(viewModel.state.value, navController = navController)
            }
        }

    }
}