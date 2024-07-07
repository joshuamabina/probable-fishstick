package com.example.smartmovie

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private const val baseUrl = "https://api.themoviedb.org/"

    const val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkNzBhYTJjNTMxOGZhNzgyNjM5NzZlYWIxNjg4YjRiMiIsIm5iZiI6MTcyMDMzMjU1Mi4wNzExMTQsInN1YiI6IjY2ODkxYjU0NzUzNjY1NjY1YjllZGEwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LkJZT_Trs16NSqqw0oOO3HAE_B8uHR5DduTk6nAFu8c"

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}