package com.nikita.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.compose.favourites.FavouriteItem
import com.nikita.filmapp.databinding.FavouritesFragmentBinding
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.utils.IMG_URL
import com.nikita.filmapp.viewModels.MovieViewModel


class FavouritesFragment : Fragment() {
    private var _binding: FavouritesFragmentBinding? = null

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
        viewModel.getFavouriteMovies().observe(viewLifecycleOwner) {
            showFavorites(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showFavorites(movies: List<Movie>) {
        binding.favouritesList.setContent {
            LazyColumn(content = {
                items(movies.size) { index ->
                    FavouriteItem(
                        title = movies[index].title,
                        imageURL = IMG_URL + movies[index].poster,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }, modifier = Modifier.padding(bottom = 72.dp))
        }
    }
}