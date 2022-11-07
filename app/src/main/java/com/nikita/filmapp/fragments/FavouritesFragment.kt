package com.nikita.filmapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FavouriteFilm.FavouriteFilmAdapter
import com.nikita.filmapp.adapter.SwipeToDeleteCallback
import com.nikita.filmapp.databinding.FavouritesFragmentBinding
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.models.filmLists
import com.nikita.filmapp.services.api.RetrofitInstance
import com.nikita.filmapp.viewModels.MovieViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.log

class FavouritesFragment : Fragment() {
    private var _binding: FavouritesFragmentBinding? = null

//    private val viewModel: MovieViewModel by activityViewModels { (activity as MainActivity).viewModelFactory }

    private val viewModel: MovieViewModel by activityViewModels { (activity as MainActivity).viewModelFactory }

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
        viewModel.getFavouriteMovies().observe(viewLifecycleOwner) {
            initOrUpdateRecyclerView(it)
        }
    }


    private fun initOrUpdateRecyclerView(movies: List<Movie> = emptyList()) {
        val favAdapter = FavouriteFilmAdapter(movies)
        binding.rvFavouriteFilms.adapter = favAdapter
        binding.rvFavouriteFilms.layoutManager =
            GridLayoutManager(activity, resources.getInteger(R.integer.grid_columns))

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

//                TODO: swipe to delete
//                val position = viewHolder.adapterPosition
//                val film = favouriteFilms[position]
//                favouriteFilms.removeAt(position)
//                favAdapter.notifyItemRemoved(position)
//
//                val message = resources.getString(R.string.deleted_message)
//                val undo = resources.getString(R.string.snackbar_undo)
//
//                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).apply {
//                    setAction(undo) {
//                        favouriteFilms.add(position, film)
//                        favAdapter.notifyItemInserted(position)
//                    }.show()
//                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvFavouriteFilms)
    }

    companion object {
        public const val TAG = "FavouritesFragment"
    }

}