package com.nikita.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
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

    @OptIn(ExperimentalMaterialApi::class)
    private fun showFavorites(movies: List<Movie>) {
        binding.favouritesList.setContent {
            LazyColumn(content = {
                items(movies.size, { index -> movies[index].id }) { index ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart) {
                                viewModel.removeMovieFromFavourites(movies[index])
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.5f) },
                        background = {
                            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val color by animateColorAsState(
                                targetValue = when (dismissState.targetValue) {
                                    DismissValue.DismissedToStart -> MaterialTheme.colorScheme.error
                                    else -> Color.Transparent
                                }
                            )

                            val icon = when (direction) {
                                DismissDirection.EndToStart -> Icons.Default.Delete
                                else -> Icons.Default.Done
                            }

                            val scale by animateFloatAsState(targetValue = if (dismissState.targetValue == DismissValue.Default) 0.75f else 1.2f)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(color)
                                    .padding(start = 12.dp, end = 12.dp)
                            ) {
                                Icon(
                                    icon, contentDescription = null, modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .scale(scale)
                                )

                            }

                        },
                        dismissContent = {
                            FavouriteItem(
                                title = movies[index].title,
                                imageURL = IMG_URL + movies[index].poster,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    )
                }
            }, modifier = Modifier.padding(bottom = 72.dp))
        }
    }
}