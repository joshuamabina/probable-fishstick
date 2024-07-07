package com.example.smartmovie

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "movies")
class Movie (
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val originalTitle: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: Double,
)