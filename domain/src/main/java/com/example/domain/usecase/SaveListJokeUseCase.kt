package com.example.domain.usecase

import com.example.domain.models.JokeItem
import com.example.domain.repository.JokeRepository

class SaveListJokeUseCase(private val jokeRepository: JokeRepository) {

    fun addListJokeToDb(listJoke: List<JokeItem>) = jokeRepository.addListJokeToDb(listJoke)
}