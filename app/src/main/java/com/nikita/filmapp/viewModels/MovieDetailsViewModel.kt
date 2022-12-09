package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikita.filmapp.models.DetailedMovie
import com.nikita.filmapp.repository.MoviesRepository
import kotlinx.coroutines.launch

//TODO: implement UI state (loading, error, detailedMovie)
//TODO: Исправить тайтл для фрагмента с деталями, добавить кнопку лайка на тулбар слева сверху
class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _detailedMovie = MutableLiveData<DetailedMovie>()
    val detailedMovie: LiveData<DetailedMovie>
        get() = _detailedMovie

    fun getMovieDetails(movieID: Long) = viewModelScope.launch {
        val response = repository.getMovieDetails(movieID)
        _detailedMovie.value = response.body()

    }


}