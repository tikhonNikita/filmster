package com.nikita.filmapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val IMG_URL =  "https://image.tmdb.org/t/p/original/"

@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
)