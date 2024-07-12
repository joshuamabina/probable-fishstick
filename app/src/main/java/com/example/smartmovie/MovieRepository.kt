package com.example.smartmovie

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }
}
