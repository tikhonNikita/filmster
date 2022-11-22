package com.nikita.filmapp.repository

import com.nikita.filmapp.db.MovieDataBase
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.services.api.RetrofitInstance
import com.nikita.filmapp.services.api.movies.MoviesAPIResponse
import retrofit2.Response


class MoviesRepository(val db: MovieDataBase) {
    suspend fun getTrendingMovies(): Response<MoviesAPIResponse> {
        return RetrofitInstance.api.getTrending()
    }

    suspend fun upsert(movie: Movie) = db.getMovieDao().upsert(movie)

    fun getSavedNews() = db.getMovieDao().getAllMovies()

    suspend fun removeFromDB(movie: Movie) = db.getMovieDao().deleteMovie(movie)

}