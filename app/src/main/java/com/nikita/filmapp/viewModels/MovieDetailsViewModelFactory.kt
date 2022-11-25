package com.nikita.filmapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikita.filmapp.repository.MoviesRepository

class MovieDetailsViewModelFactory(
    private val moviesRepository: MoviesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(moviesRepository) as T
    }
}