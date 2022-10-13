package com.nikita.filmapp.models

import com.google.gson.annotations.SerializedName

//https://image.tmdb.org/t/p/original/
data class Movie(
    val id: String,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
)