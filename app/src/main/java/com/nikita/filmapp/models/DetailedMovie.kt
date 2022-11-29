package com.nikita.filmapp.models

import com.google.gson.annotations.SerializedName

data class DetailedMovie(
    val id: Number = 0,
    val backdrop: String = "",
    val poster: String = "",
    val title: String = "",
    val overview: String = "",
    val productionCompany: String = "",
    val genres: List<String> = emptyList(),
    val releaseDate: String = "",
    val rating: Double = 0.0
)
