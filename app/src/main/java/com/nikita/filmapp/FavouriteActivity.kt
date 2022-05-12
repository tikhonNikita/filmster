package com.nikita.filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.filmapp.MainActivity.Companion.FAV_FILMS
import com.nikita.filmapp.adapter.FavouriteFilm.FavouriteFilmAdapter
import com.nikita.filmapp.databinding.ActivityFavouriteBinding
import com.nikita.filmapp.databinding.ActivityMainBinding
import com.nikita.filmapp.databinding.FavouriteFilmBinding
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.filmLists
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.log

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var favouriteFilms: List<Film>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteFilms = initFilms(intent.getStringExtra(FAV_FILMS))
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

    }


    private fun initRecyclerView() {
        Log.d("PIZDA", favouriteFilms.toString())
        binding.rvFavouriteFilms.adapter = FavouriteFilmAdapter(favouriteFilms)
        binding.rvFavouriteFilms.layoutManager = LinearLayoutManager(this)
    }

    private fun initFilms(encodedFilms: String?): List<Film> {
        return if (encodedFilms != null) {
            Json.decodeFromString(encodedFilms)
        } else {
            listOf()
        }
    }
}