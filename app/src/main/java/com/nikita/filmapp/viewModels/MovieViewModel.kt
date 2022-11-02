package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel(val repository: MoviesRepository) : ViewModel() {
    private val _movies = MutableLiveData<MutableList<Movie>>()


    fun getSavedNews() = repository.getSavedNews()


    val movies: LiveData<MutableList<Movie>>
        get() = _movies


    fun loadFilms() {
        viewModelScope.launch {
            val data = repository.getTrendingMovies()
            data.body()?.let {
                setFilms(it.results)
                it.results.forEach { movie ->
                    repository.upsert(movie)
                }
            }
        }
    }


    private fun setFilms(movies: List<Movie>) {
        _movies.postValue(movies.toMutableList())
    }
}