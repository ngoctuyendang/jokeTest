package com.example.domain.repository

import com.example.domain.models.JokeItem
import io.reactivex.Completable
import io.reactivex.Single

interface JokeRepository {
    fun getListJoke(): Single<ArrayList<JokeItem>>
    fun voteForJoke(jokeId: Int, isFunny: Boolean): Completable
    fun addListJokeToDb(listJoke: List<JokeItem>): Completable
}