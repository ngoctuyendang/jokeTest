package com.example.domain.usecase

import com.example.domain.repository.JokeRepository

class VoteForJokeUseCase(private val jokeRepository: JokeRepository) {

    fun voteForJoke(jokeId: Int, isFunny: Boolean) = jokeRepository.voteForJoke(jokeId, isFunny)
}