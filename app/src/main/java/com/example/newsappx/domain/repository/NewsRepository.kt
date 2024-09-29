package com.example.newsappx.domain.repository

import com.example.newsappx.domain.model.Article
import com.example.newsappx.presentation.state.NewsState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(onSearch: String): Flow<NewsState<List<Article>>>

}