package com.example.newsappx.presentation.state

sealed class NewsState<out T> {
    data class Success<T>(val data: T) : NewsState<T>()
    data class Error(val error: String) : NewsState<Nothing>()
    data object Loading : NewsState<Nothing>()
}