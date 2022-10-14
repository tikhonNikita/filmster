package com.nikita.filmapp.services.api.movies

import com.nikita.filmapp.models.Movie

data class MoviesAPIResponse(
    val page: Int,
    val results: List<Movie>
)