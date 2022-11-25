package com.nikita.filmapp.repository

import com.nikita.filmapp.db.MovieDataBase
import com.nikita.filmapp.models.DetailedMovie
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.services.api.RetrofitInstance
import com.nikita.filmapp.services.api.movies.MoviesAPIResponse
import retrofit2.Response

/*
Movie details:
    id - id
    backdrop_path - ivDetails backdrop_path
    poster_path - ivPoster
    overview - tvDetailsFilmDescription
    original_title - title
 */


class MoviesRepository(val db: MovieDataBase) {
    suspend fun getTrendingMovies(): Response<MoviesAPIResponse> {
        return RetrofitInstance.api.getTrending()
    }


    suspend fun getMovieDetails(movieId: Int): Response<DetailedMovie> {
        return RetrofitInstance.api.getMovieDetails(movieId)
    }

    suspend fun upsert(movie: Movie) = db.getMovieDao().upsert(movie)

    fun getSavedNews() = db.getMovieDao().getAllMovies()

    suspend fun removeFromDB(movie: Movie) = db.getMovieDao().deleteMovie(movie)

}