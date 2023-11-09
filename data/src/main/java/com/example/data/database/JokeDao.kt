package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.JokeItemDto
import io.reactivex.Completable

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllJoke(jokeList: List<JokeItemDto>): Completable

    @Query("UPDATE joke_stories SET funny_status=:funnyStatus WHERE id = :jokeId")
    fun updateFunnyStatus (jokeId: Int, funnyStatus: Int): Completable
}