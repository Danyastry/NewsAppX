package com.example.newsappx.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappx.data.local.Dao
import com.example.newsappx.domain.model.Article
import com.example.newsappx.presentation.state.NewsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val dao: Dao) : ViewModel() {

    private val _state = MutableStateFlow<NewsState<Boolean>>(NewsState.Loading)
    val state = _state as StateFlow<NewsState<Boolean>>


    fun addArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.emit(NewsState.Loading)
                if (getArticle(article.url) == null) {
                    dao.upsert(article)
                }
                _state.emit(NewsState.Success(true))
            } catch (e: Exception) {
                _state.emit(NewsState.Error(e.message.toString()))
            }
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.emit(NewsState.Loading)
                dao.delete(article)
                _state.emit(NewsState.Success(true))
            } catch (e: Exception) {
                _state.emit(NewsState.Error(e.message.toString()))
            }
        }
    }

    suspend fun getArticle(url: String): Article? {
        return withContext(Dispatchers.IO) {
            dao.getArticle(url)
        }
    }
}