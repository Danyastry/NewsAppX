package com.example.newsappx.di.modules

import com.example.newsappx.data.local.Dao
import com.example.newsappx.domain.repository.NewsRepository
import com.example.newsappx.presentation.bookmark.BookmarkViewModel
import com.example.newsappx.presentation.details.DetailsViewModel
import com.example.newsappx.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get<NewsRepository>()) }

    viewModel { DetailsViewModel(get<Dao>()) }

    viewModel { BookmarkViewModel(get<Dao>()) }
}