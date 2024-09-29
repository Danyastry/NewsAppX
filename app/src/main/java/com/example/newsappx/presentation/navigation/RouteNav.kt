package com.example.newsappx.presentation.navigation

import com.example.newsappx.domain.model.Article
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object Route {
    fun createNewsDetails(article: Article): String {
        val encodedImageUrl = article.urlToImage?.let { URLEncoder.encode(it, "UTF-8") } ?: ""
        val encodedUrl = URLEncoder.encode(article.url, "UTF-8")
        val tempNews = article.copy(urlToImage = encodedImageUrl, url = encodedUrl)
        val gson = Gson().toJson(tempNews)
        val encodedJson = URLEncoder.encode(gson, "UTF-8").replace("+", "%20")
        return "details/article=$encodedJson"
    }

    fun getArticleFromGson(json: String): Article {
        val article = Gson().fromJson(json, Article::class.java)
        val decodedImageUrl = article.urlToImage?.let { URLDecoder.decode(it, "UTF-8") } ?: ""
        val decodedUrl = URLDecoder.decode(article.url, "UTF-8").replace("+", "%20")
        return article.copy(urlToImage = decodedImageUrl, url = decodedUrl)

    }
}

object RouteNav {
    const val APP_START_NAVIGATION = "appStartNavigation"
    const val NEWS_NAVIGATION = "newsNavigation"
    const val DETAILS_SCREEN = "details/article={articleJson}"
    const val BOOKMARK = "bookmark"
    const val ARTICLE_JSON = "articleJson"
}