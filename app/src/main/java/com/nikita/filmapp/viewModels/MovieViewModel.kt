package com.nikita.filmapp.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel(val repository: MoviesRepository) : ViewModel() {
    private val _movies = MutableLiveData<MutableList<Movie>>()

    val movies: LiveData<MutableList<Movie>>
        get() = _movies


    fun loadFilms() {
        viewModelScope.launch {
            val data = repository.getTrendingMovies()
            data.body()?.let { addFirstFilm(it.results) }
        }
    }


    private fun addFirstFilm(movies: List<Movie>) {
        _movies.postValue(movies.toMutableList())
    }
}