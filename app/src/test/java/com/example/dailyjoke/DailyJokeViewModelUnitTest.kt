package com.example.dailyjoke

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dailyjoke.ui.joke.DailyJokeViewModel
import com.example.domain.models.JokeItem
import com.example.domain.usecase.GetListJokeUseCase
import com.example.domain.usecase.SaveListJokeUseCase
import com.example.domain.usecase.VoteForJokeUseCase
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DailyJokeViewModelUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getListJokeUseCase = Mockito.mock(GetListJokeUseCase::class.java)
    private val saveListJokeUseCase = Mockito.mock(SaveListJokeUseCase::class.java)
    private val voteForJokeUseCase = Mockito.mock(VoteForJokeUseCase::class.java)
    private val viewModel by lazy {
        DailyJokeViewModel(
            getListJokeUseCase,
            voteForJokeUseCase,
            saveListJokeUseCase
        )
    }

    @Before
    fun initTest() {
        reset(getListJokeUseCase, voteForJokeUseCase, saveListJokeUseCase)
    }

    @Test
    fun testJokeListViewModel_getJokeList_Error() {
        val errorMessage = "Error response"
        val response = Throwable(errorMessage)
        whenever(getListJokeUseCase.getListJoke())
            .thenReturn(Single.error(response))

        viewModel.error.observeForever {
            assert(it.message == "errorMessage")
        }
    }

    @Test
    fun testJokeListViewModel_getJokeList_Success() {
        val data = arrayListOf(
            JokeItem(
                111,
                "This is content",
                -1
            )
        )

        whenever(getListJokeUseCase.getListJoke())
            .thenReturn(Single.just(data))
        viewModel.listJoke.observeForever {
            assert(it.size == data.size)
        }
    }
}