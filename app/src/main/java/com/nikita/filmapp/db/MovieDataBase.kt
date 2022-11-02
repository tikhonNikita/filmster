package com.nikita.filmapp.db

import android.content.Context
import androidx.room.*
import com.nikita.filmapp.models.Movie

@Database(
    entities = [Movie::class],
    version = 1
)

abstract class MovieDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDataBase::class.java,
            "movie_db.db"
        ).build()
    }

}