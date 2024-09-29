package com.example.newsappx.data.model

import com.example.newsappx.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)