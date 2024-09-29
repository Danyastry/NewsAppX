package com.example.newsappx.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsappx.presentation.bookmark.state.BookmarkState
import com.example.newsappx.presentation.common.ArticlesList
import com.example.newsappx.presentation.navigation.Route
import com.example.newsappx.ui.theme.montserrat_bold
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    viewModel: BookmarkViewModel = koinViewModel(),
    navController: NavController
) {
    val getAllArticles = viewModel.getArticles().collectAsState(initial = state.articles)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bookmark", fontFamily = montserrat_bold, fontSize = 30.sp, color = Color.White)
        Spacer(Modifier.height(15.dp))
        ArticlesList(
            article = getAllArticles.value,
            onClick = { navController.navigate(Route.createNewsDetails(it)) })
    }
}