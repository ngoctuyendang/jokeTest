package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.JokeItemDto

@Database(entities = [JokeItemDto::class], version = 1)
abstract class JokeDatabase: RoomDatabase() {

    abstract fun jokeDao(): JokeDao
}