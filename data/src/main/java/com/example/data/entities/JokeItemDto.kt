package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.JokeItem

@Entity(tableName = "joke_stories")
data class JokeItemDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "content")
    val content: String = "",
    @ColumnInfo(name = "funny_status")
    val funnyStatus: Int = -1, // -1: have not voted, 1: funny, 0: not funny
)

fun List<JokeItem>.mapListJokeToJokeDto(): List<JokeItemDto> {
    return this.map {
        it.mapJokeToJokeDto()
    }
}

fun JokeItem.mapJokeToJokeDto(): JokeItemDto {
    return JokeItemDto(id = this.id, content = this.content, funnyStatus = this.funnyStatus)
}





