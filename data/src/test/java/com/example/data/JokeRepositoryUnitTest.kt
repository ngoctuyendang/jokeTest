package com.example.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.database.JokeDao
import com.example.data.database.JokeDatabase
import com.example.data.entities.JokeItemDto
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class JokeRepositoryUnitTest {

    private lateinit var jokeDao: JokeDao

    private lateinit var db: JokeDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, JokeDatabase::class.java
        )
            .allowMainThreadQueries() // Only for testing
            .build()
        jokeDao = db.jokeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveJokeListCheck() {
        val data = arrayListOf(
            JokeItemDto(
                id = 1,
                content = "This is content",
                funnyStatus = 1
            )
        )
        jokeDao.addAllJoke(data).test().assertComplete()
        val getJoke = jokeDao.getJokeWithId(1)
        assertThat(getJoke, equalTo(data[0]))
    }

    @Test
    fun updateJokeInfoCheck() {
        val jokeId = 1
        val data = arrayListOf(
            JokeItemDto(
                id = 1,
                content = "This is content",
                funnyStatus = -1 // 1: funny, 0: not funny, -1: have not voted
            )
        )
        jokeDao.addAllJoke(data).test().assertComplete()
        jokeDao.updateFunnyStatus(jokeId, 1).test().assertComplete()
        val getJoke = jokeDao.getJokeWithId(jokeId)
        assertThat(getJoke.funnyStatus, equalTo(1))
    }
}