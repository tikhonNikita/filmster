package com.nikita.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FavouriteFilm.FavouriteFilmAdapter
import com.nikita.filmapp.adapter.SwipeToDeleteCallback

import com.nikita.filmapp.databinding.FavouritesFragmentBinding
import com.nikita.filmapp.models.Film
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FavouritesFragment : Fragment() {
    private var _binding: FavouritesFragmentBinding? = null
    private lateinit var favouriteFilms: MutableList<Film>

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmsString = arguments?.getString(FAV_FILMS_KEY) ?: error("DATA IS MANDATORY")
        favouriteFilms = Json.decodeFromString(filmsString)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        val favAdapter = FavouriteFilmAdapter(favouriteFilms)
        binding.rvFavouriteFilms.adapter = favAdapter
        binding.rvFavouriteFilms.layoutManager =
            GridLayoutManager(activity, resources.getInteger(R.integer.grid_columns))

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

    override fun onDestroy() {
        super.onDestroy()
        val bundle = Bundle()
        bundle.apply {
            putString(FAV_FRAGMENT_DATA, Json.encodeToString(favouriteFilms))
        }
        parentFragmentManager.setFragmentResult(FAV_FRAGMENT_RESULT, bundle)
    }


    companion object {
        fun create(films: List<Film>): FavouritesFragment {
            val stringedFilms = Json.encodeToString(films)
            val fragmentToGo = FavouritesFragment()
            val args = Bundle().apply {
                putString(FAV_FILMS_KEY, stringedFilms)
            }
            fragmentToGo.arguments = args
            return fragmentToGo
        }

        private const val FAV_FILMS_KEY = "FAV_FILMS_KEY"
        const val FAV_FRAGMENT_RESULT = "FAV_FRAGMENT_RESULT"
        const val FAV_FRAGMENT_DATA = "FAV_FRAGMENT_DATA"
    }

}