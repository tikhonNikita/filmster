package com.nikita.filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.databinding.ActivityMainBinding
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.filmLists
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmList: List<Film>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        filmList = if (savedInstanceState?.getString(FILMS_LIST) != null) {
            val data = savedInstanceState.getString(FILMS_LIST)
            Json.decodeFromString(data!!)
        } else {
            listOf(
                Film(1, "Batman", R.drawable.batman, "Film about batman", false),
                Film(2, "Iron man 3", R.drawable.iron_man, filmLists[1].description, false),
                Film(
                    3,
                    "Doctor Strange in the Multiverse of Madness",
                    R.drawable.multiverse_of_madness,
                    "Doctor Strange in the Multiverse of Madness film ",
                    false
                ),
            )
        }

        val view = binding.root
        setContentView(view)
        initRecyclerView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val data = Json.encodeToString(filmList)
        outState.putString(FILMS_LIST, data)
    }

    private fun initRecyclerView() {
        binding.rvFilmList.apply {
            adapter = FilmsAdapter(filmList)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    companion object {
        const val FILMS_LIST = "films"

    }
}