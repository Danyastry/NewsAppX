package com.example.newsappx.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappx.domain.model.Article
import com.example.newsappx.domain.repository.NewsRepository
import com.example.newsappx.presentation.state.NewsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsState = MutableStateFlow<NewsState<List<Article>>>(NewsState.Loading)
    val newsState = _newsState.asStateFlow()

    init {
        getNews()
    }

    fun getNews(onSearch: String = "BBC") {
        viewModelScope.launch {
            try {
                _newsState.value = NewsState.Loading
                newsRepository.getNews(onSearch).collect { state ->
                    _newsState.value = state
                }
            } catch (e: Exception) {
                NewsState.Error(e.message.toString())
            }
        }
    }
}