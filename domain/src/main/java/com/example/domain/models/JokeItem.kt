package com.example.domain.models

data class JokeItem(
    val id: Int,
    val content: String = "",
    val funnyStatus: Int = -1 // -1: have not voted, 1: funny, 0: not funny
)