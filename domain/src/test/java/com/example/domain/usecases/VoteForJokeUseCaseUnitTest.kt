package com.example.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.mock
import com.example.domain.repository.JokeRepository
import com.example.domain.usecase.VoteForJokeUseCase
import com.example.domain.whenever
import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test

class VoteForJokeUseCaseUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val jokeRepository: JokeRepository = mock<JokeRepository>()

    private val voteForJokeUseCase by lazy { VoteForJokeUseCase(jokeRepository) }

    @Test
    fun testVoteForJokeUseCase_voteForJoke_Completed() {
        whenever(jokeRepository.voteForJoke(1, true))
            .thenReturn(Completable.complete())

        voteForJokeUseCase.voteForJoke(1, true)
            .test()
            .assertComplete()
    }

    @Test
    fun testVoteForJokeUseCase_voteForJoke_Error() {
        val response = Throwable("Error response")
        whenever(voteForJokeUseCase.voteForJoke(1, true))
            .thenReturn(Completable.error(response))

        voteForJokeUseCase.voteForJoke(1, true)
            .test()
            .assertError(response)
    }
}