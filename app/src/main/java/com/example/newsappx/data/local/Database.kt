package com.example.newsappx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappx.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun dao(): Dao

}