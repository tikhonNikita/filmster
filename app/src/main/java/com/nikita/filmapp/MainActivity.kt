package com.nikita.filmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

        val startAnotherActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                val liked = data.getBooleanExtra(FILM_LIKED, false)
                if (data.hasExtra(COMMENTS)) {
                    val comments = data.getStringExtra(COMMENTS)
                    Toast.makeText(this, "Film ${if (liked) "is" else "not"} " + comments, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }


        val view = binding.root
        setContentView(view)
        initRecyclerView(startAnotherActivity)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val data = Json.encodeToString(filmList)
        outState.putString(FILMS_LIST, data)
    }

    private fun initRecyclerView(activityStarter: ActivityResultLauncher<Intent>) {
        binding.rvFilmList.apply {
            adapter = FilmsAdapter(filmList) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.DATA_KEY, Json.encodeToString(it))
                activityStarter.launch(intent)
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    companion object {
        const val FILMS_LIST = "films"
        const val FILM_LIKED = "film_liked"
        const val COMMENTS = "comments"
        const val REQUEST_CODE = 321
    }
}