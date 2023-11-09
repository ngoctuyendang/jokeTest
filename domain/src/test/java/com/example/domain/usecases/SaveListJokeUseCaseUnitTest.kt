package com.example.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.mock
import com.example.domain.repository.JokeRepository
import com.example.domain.usecase.SaveListJokeUseCase
import com.example.domain.whenever
import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test

class SaveListJokeUseCaseUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val jokeRepository: JokeRepository = mock<JokeRepository>()

    private val saveListJokeUseCase by lazy { SaveListJokeUseCase(jokeRepository) }

    @Test
    fun testSaveListJokeUseCase_addListJokeToDb_Completed() {
        whenever(jokeRepository.addListJokeToDb(arrayListOf()))
            .thenReturn(Completable.complete())

        saveListJokeUseCase.addListJokeToDb(arrayListOf())
            .test()
            .assertComplete()
    }

    @Test
    fun testSaveListJokeUseCase_addListJokeToDb_Error() {
        val response = Throwable("Error response")
        whenever(jokeRepository.addListJokeToDb(arrayListOf()))
            .thenReturn(Completable.error(response))

        saveListJokeUseCase.addListJokeToDb(arrayListOf())
            .test()
            .assertError(response)
    }
}