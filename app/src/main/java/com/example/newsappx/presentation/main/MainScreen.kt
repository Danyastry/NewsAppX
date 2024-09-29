package com.example.newsappx.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsappx.presentation.emptyScreen.EmptyScreen
import com.example.newsappx.presentation.common.ArticleCard
import com.example.newsappx.presentation.common.ArticleShimmerEffect
import com.example.newsappx.presentation.navigation.Route
import com.example.newsappx.presentation.search.SearchBar
import com.example.newsappx.presentation.state.NewsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    navController: NavController
) {

    val newsState by mainViewModel.newsState.collectAsState()
    val listState = rememberLazyListState()
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .padding(top = 25.dp)
    ) {
        SearchBar(
            text = searchText,
            onValueChange = {
                searchText = it
                mainViewModel.getNews(it)
            },
            modifier = Modifier.padding(top = 15.dp)
        )

        when (newsState) {
            NewsState.Loading -> {
                LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                    items(10) {
                        ArticleShimmerEffect()
                    }
                }
            }

            is NewsState.Success -> {
                val data = newsState as NewsState.Success
                LazyColumn(state = listState, modifier = Modifier.padding(top = 20.dp)) {
                    items(data.data) { article ->
                        ArticleCard(article) {
                            navController.navigate(Route.createNewsDetails(article))
                        }
                    }
                }
            }

            is NewsState.Error -> {
                EmptyScreen(error = newsState as NewsState.Error)
            }
        }
    }
}
