package com.example.newsappx.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsappx.data.local.Dao
import com.example.newsappx.presentation.bookmark.state.BookmarkState

class BookmarkViewModel(private val dao: Dao) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    fun getArticles() = dao.getAllArticles()

}