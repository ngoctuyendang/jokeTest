package com.example.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.mock
import com.example.domain.models.JokeItem
import com.example.domain.repository.JokeRepository
import com.example.domain.usecase.GetListJokeUseCase
import com.example.domain.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class GetListJokeUseCasesUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val jokeRepository: JokeRepository = mock<JokeRepository>()

    private val getListJokeUseCase by lazy { GetListJokeUseCase(jokeRepository) }

    @Test
    fun testGetListJokeUseCase_getListJoke_Completed() {
        whenever(jokeRepository.getListJoke())
            .thenReturn(Single.just(arrayListOf()))

        getListJokeUseCase.getListJoke()
            .test()
            .assertComplete()
    }

    @Test
    fun testGetListJokeUseCase_getListJoke_Error() {
        val response = Throwable("Error response")
        whenever(jokeRepository.getListJoke())
            .thenReturn(Single.error(response))

        getListJokeUseCase.getListJoke()
            .test()
            .assertError(response)
    }

    @Test
    fun testGetListJokeUseCase_getListJoke_response() {
        val jokeItem = JokeItem(
            id = 101,
            content = "Joke content test",
            funnyStatus = 1
        )
        val response = arrayListOf(jokeItem)
        val expectedList = arrayListOf(jokeItem)
        whenever(jokeRepository.getListJoke())
            .thenReturn(Single.just(response))

        getListJokeUseCase.getListJoke()
            .test()
            .assertValue(expectedList)
    }
}