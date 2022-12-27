package com.nikita.filmapp.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.databinding.DetailsFragmentBinding
import com.nikita.filmapp.models.DetailedMovie
import com.nikita.filmapp.utils.IMG_URL
import com.nikita.filmapp.viewModels.MovieDetailsViewModel


private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels { (activity as MainActivity).movieDetailsViewModelFactory }

    private var _binding: DetailsFragmentBinding? = null

    val args: DetailsFragmentArgs by navArgs()


    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

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
            tvRatingValue.text = movie.rating.toString()
            Glide.with(requireContext()).load(IMG_URL + movie.backdrop)
                .into(ivPoster)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar!!.hide()
        val posterURL = args.posterURL
        Glide.with(requireContext()).load(posterURL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                   return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }


            })
            .into(binding.ivDetails)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        public const val TAG = "DetailsFragment"
    }

}