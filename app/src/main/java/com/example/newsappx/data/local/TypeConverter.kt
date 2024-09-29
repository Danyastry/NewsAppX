package com.example.newsappx.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsappx.domain.model.Source

@ProvidedTypeConverter
class TypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id}, ${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        val sourceIndex = source.split(",")
        val id = sourceIndex.getOrNull(1)?.trim() ?: ""
        val name = sourceIndex.getOrNull(0)?.trim() ?: ""
        return Source(name, id)
    }
}

