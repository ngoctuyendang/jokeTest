package com.example.domain.usecase

import com.example.domain.repository.JokeRepository

class GetListJokeUseCase(private val jokeRepository: JokeRepository) {

    fun getListJoke() = jokeRepository.getListJoke()
}