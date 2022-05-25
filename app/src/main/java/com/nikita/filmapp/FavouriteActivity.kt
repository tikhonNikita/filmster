package com.nikita.filmapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nikita.filmapp.MainActivity.Companion.FAV_FILMS
import com.nikita.filmapp.adapter.FavouriteFilm.FavouriteFilmAdapter
import com.nikita.filmapp.adapter.SwipeToDeleteCallback
import com.nikita.filmapp.databinding.ActivityFavouriteBinding
import com.nikita.filmapp.models.Film
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var favouriteFilms: MutableList<Film>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteFilms = initFilms(intent.getStringExtra(FAV_FILMS))
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val favAdapter = FavouriteFilmAdapter(favouriteFilms)
        binding.rvFavouriteFilms.adapter = favAdapter
        binding.rvFavouriteFilms.layoutManager = LinearLayoutManager(this)

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val film = favouriteFilms[position]
                favouriteFilms.removeAt(position)
                favAdapter.notifyItemRemoved(position)

                val message = resources.getString(R.string.deleted_message)
                val undo = resources.getString(R.string.snackbar_undo)

                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).apply {
                    setAction(undo) {
                        favouriteFilms.add(position, film)
                        favAdapter.notifyItemInserted(position)
                    }.show()
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvFavouriteFilms)
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra(MainActivity.FAVOURITE_FILMS, Json.encodeToString(favouriteFilms))
        setResult(RESULT_OK, data)

        super.onBackPressed()
    }

    private fun initFilms(encodedFilms: String?): MutableList<Film> {
        return if (encodedFilms != null) {
            Json.decodeFromString(encodedFilms)
        } else {
            mutableListOf()
        }
    }
}