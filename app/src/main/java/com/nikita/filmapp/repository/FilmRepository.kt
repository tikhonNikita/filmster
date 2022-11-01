package com.nikita.filmapp.repository

import com.nikita.filmapp.services.api.RetrofitInstance
import com.nikita.filmapp.services.api.movies.MoviesAPIResponse
import retrofit2.Response


class MoviesRepository {
    suspend fun getTrendingMovies(): Response<MoviesAPIResponse> {
        return RetrofitInstance.api.getTrending()
    }
}