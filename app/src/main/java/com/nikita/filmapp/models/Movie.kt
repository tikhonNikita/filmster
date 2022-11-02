package com.nikita.filmapp.models

import com.google.gson.annotations.SerializedName

const val IMG_URL =  "https://image.tmdb.org/t/p/original/"
data class Movie(
    val id: String,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
)