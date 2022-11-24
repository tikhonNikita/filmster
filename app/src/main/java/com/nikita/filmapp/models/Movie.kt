package com.nikita.filmapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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