package com.example.dailyjoke.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.JokeDao
import com.example.data.database.JokeDatabase
import com.example.data.repository.JokeRepositoryImpl
import com.example.domain.repository.JokeRepository
import com.example.domain.usecase.GetListJokeUseCase
import com.example.domain.usecase.SaveListJokeUseCase
import com.example.domain.usecase.VoteForJokeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideJokeDatabase(@ApplicationContext appContext: Context): JokeDatabase {
        return Room.databaseBuilder(
            appContext,
            JokeDatabase::class.java,
            "JokeStories"
        ).build()
    }

    @Provides
    fun provideChannelDao(jokeDatabase: JokeDatabase): JokeDao {
        return jokeDatabase.jokeDao()
    }

    @Singleton
    @Provides
    fun provideJokeRepository(jokeDao: JokeDao): JokeRepository {
        return JokeRepositoryImpl(jokeDao)
    }

    @Singleton
    @Provides
    fun provideGetListJokeUseCase(jokeRepository: JokeRepository): GetListJokeUseCase {
        return GetListJokeUseCase(jokeRepository)
    }

    @Singleton
    @Provides
    fun provideSaveListJokeUseCase(jokeRepository: JokeRepository): SaveListJokeUseCase {
        return SaveListJokeUseCase(jokeRepository)
    }

    @Singleton
    @Provides
    fun provideVoteForJokeUseCase(jokeRepository: JokeRepository): VoteForJokeUseCase {
        return VoteForJokeUseCase(jokeRepository)
    }
}