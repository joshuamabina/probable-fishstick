package com.example.smartmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    fun insert(movie: Movie) {
        viewModelScope.launch {
            Log.d("MovieViewModel.insert", "movie: ${movie.originalTitle}")
            repository.insert(movie)
        }
    }

    fun getMovies(): LiveData<List<Movie>> = liveData {
        Log.d("MovieViewModel.get", "method.Call")
        val movies = repository.getAllMovies()
        emit(movies)
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
