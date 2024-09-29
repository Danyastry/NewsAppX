package com.example.newsappx.di.modules

import androidx.room.Room
import com.example.newsappx.data.api.BASE_URL
import com.example.newsappx.data.api.NewsApi
import com.example.newsappx.data.local.Dao
import com.example.newsappx.data.local.Database
import com.example.newsappx.data.local.TypeConverter
import com.example.newsappx.data.repository.NewsRepositoryImpl
import com.example.newsappx.domain.repository.NewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }

    single {
        Room.databaseBuilder(
            get(),
            klass = Database::class.java,
            name = "db"
        ).addTypeConverter(TypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    single<NewsRepository> { NewsRepositoryImpl(get()) }

    single<Dao> { get<Database>().dao() }
}
