package com.example.newsappx.data.api

import com.example.newsappx.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "3b276f33e173431788bb7f3c1c5bd5fe"
const val BASE_URL = "https://newsapi.org/v2/"

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "BBC",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}