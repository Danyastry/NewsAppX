package com.example.newsappx.data.repository

import coil.network.HttpException
import com.example.newsappx.data.api.NewsApi
import com.example.newsappx.domain.model.Article
import com.example.newsappx.domain.repository.NewsRepository
import com.example.newsappx.presentation.state.NewsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getNews(onSearch: String): Flow<NewsState<List<Article>>> =
        flow {
            emit(NewsState.Loading)
            try {
                val response = newsApi.getNews(onSearch)
                val article = response.articles
                emit(NewsState.Success(article))
            } catch (e: HttpException) {
                emit(NewsState.Error("Server error: ${e.message}"))
            } catch (e: IOException) {
                emit(NewsState.Error("Network error: ${e.message}"))
            } catch (e: Exception) {
                emit(NewsState.Error("Unexpected error: ${e.message}"))

            }
        }
}