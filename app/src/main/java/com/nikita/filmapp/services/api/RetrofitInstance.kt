package com.nikita.filmapp.services.api

import com.google.gson.GsonBuilder
import com.nikita.filmapp.BuildConfig
import com.nikita.filmapp.models.DetailedMovie
import com.nikita.filmapp.services.api.movies.DetailsMovieDeserializer
import com.nikita.filmapp.services.api.movies.MoviesAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            val customGson = GsonBuilder().registerTypeAdapter(DetailedMovie::class.java, DetailsMovieDeserializer()).create()

            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(customGson))
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(MoviesAPI::class.java)
        }
//            val logging = HttpLoggingInterceptor()
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build()
//            Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//
//        val api by lazy {
//            retrofit.create(NewsApi::class.java)
//        }
    }
}