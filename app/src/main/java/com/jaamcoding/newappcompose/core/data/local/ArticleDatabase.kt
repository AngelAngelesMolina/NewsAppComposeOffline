package com.jaamcoding.newappcompose.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val dao: ArticlesDao
}