package com.nikita.filmapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.databinding.DetailsFragmentBinding
import com.nikita.filmapp.models.DetailedMovie
import com.nikita.filmapp.models.filmLists
import com.nikita.filmapp.utils.IMG_URL
import com.nikita.filmapp.viewModels.MovieDetailsViewModel


private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels { (activity as MainActivity).movieDetailsViewModelFactory }

    private var _binding: DetailsFragmentBinding? = null

    val args: DetailsFragmentArgs by navArgs()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()
        val movieID = args.movieID
        viewModel.getMovieDetails(movieID)
        viewModel.detailedMovie.observe(viewLifecycleOwner, ::fillMovie)
        return binding.root

    }


    private fun fillMovie(movie: DetailedMovie) {
        binding.run {
            tvDetailsFilmTitle.text = movie.title
            tvDetailsGenreValue.text = movie.genres.joinToString(", ")
            tvReleaseDateValue.text = movie.releaseDate
            tvDetailsFilmDescription.text = movie.overview
            Glide.with(requireContext()).load(IMG_URL + movie.poster)
                .into(ivDetails)
            Glide.with(requireContext()).load(IMG_URL + movie.backdrop)
                .into(ivPoster)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar!!.hide()

        val film = filmLists[1]
        binding.apply {
            ivDetails.setImageResource(film.image)
            tvDetailsFilmDescription.text = film.description
            tvDetailsFilmTitle.text = film.title
            tbDetails.title = film.title
            btnAddFriend.setOnClickListener {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Go and watch this film \"${film.title}\"")
                sendIntent.type = "text/plain"

                startActivity(sendIntent)
            }
            binding.btnRateTheFilm.setOnClickListener {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        public const val TAG = "DetailsFragment"
    }

}