package com.example.smartmovie

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieService {
    @GET("/3/discover/movie")
    suspend fun getMovies(@Header("Authorization") token: String) : Response<ApiResponse>
}