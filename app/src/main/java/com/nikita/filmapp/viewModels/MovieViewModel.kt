package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movies = MutableLiveData<MutableList<Movie>>()
    private val _savedMovies = MutableLiveData<MutableList<Movie>>()


    fun setFavouriteMovie(movie: Movie) = viewModelScope.launch {
        repository.upsert(movie)
    }

    fun getFavouriteMovies() = repository.getSavedNews()


    val movies: LiveData<MutableList<Movie>>
        get() = _movies


    fun loadFilms() {
        viewModelScope.launch {
            val data = repository.getTrendingMovies()
            data.body()?.let {
                setFilms(it.results)
            }
        }
    }


    private fun setFilms(movies: List<Movie>) {
        _movies.postValue(movies.toMutableList())
    }
}