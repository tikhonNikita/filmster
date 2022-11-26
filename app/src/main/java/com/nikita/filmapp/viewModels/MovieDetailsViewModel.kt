package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikita.filmapp.models.DetailedMovie
import com.nikita.filmapp.repository.MoviesRepository
import kotlinx.coroutines.launch

//TODO: implement UI state (loading, error, detailedMovie)
class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _detailedMovie = MutableLiveData<DetailedMovie>()
    val detailedMovie: LiveData<DetailedMovie>
        get() = _detailedMovie

    fun getMovieDetails(movieID: Long) = viewModelScope.launch {
        val response = repository.getMovieDetails(movieID)
        _detailedMovie.value = response.body()

    }


}