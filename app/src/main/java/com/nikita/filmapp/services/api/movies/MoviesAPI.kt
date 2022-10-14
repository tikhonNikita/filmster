package com.nikita.filmapp.services.api.movies

import com.nikita.filmapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesAPI {
    @GET("trending/movie/week")
    suspend fun getTrending(
        @Query("api_key")
        apiKey: String = BuildConfig.MOVIE_API_KEY
    ): retrofit2.Response<MoviesAPIResponse>
}