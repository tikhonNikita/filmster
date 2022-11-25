package com.nikita.filmapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.models.Movie
import com.nikita.filmapp.viewModels.MovieViewModel


private const val TAG = "MAIN_SOBAKA_FRAGMENT"

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by activityViewModels { (activity as MainActivity).viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()

        binding.laLoader.setAnimation(R.raw.films_loading)
        binding.laLoader.repeatCount = LottieDrawable.INFINITE
        binding.laLoader.playAnimation()

        viewModel.movies.observe(viewLifecycleOwner) {
            if (it.size > 0) {
                hideLoader()
                updateRecyclerView(it)

            }
        }


        viewModel.loadFilms()

        return binding.root
    }

    private fun hideLoader() {
        binding.laLoader.visibility = View.INVISIBLE

        binding.rvFilmList.visibility = View.VISIBLE
    }

    private fun showLoader() {
        binding.rvFilmList.visibility = View.INVISIBLE
        binding.laLoader.visibility = View.VISIBLE
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(listOf())
    }

    private fun initRecyclerView(list: List<Movie>) {
        binding.rvFilmList.apply {
            adapter = FilmsAdapter(list, activity as MainActivity) {
            }
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun updateRecyclerView(movies: List<Movie>) {
        binding.rvFilmList.apply {
            adapter = FilmsAdapter(movies, activity as MainActivity) {
                viewModel.setFavouriteMovie(it)
                SnackbarHelper.showSnackbar(
                    requireView(),
                    getString(R.string.film_was_added_to_fav),
                    requireActivity().findViewById(R.id.navigate)
                )
            }
            layoutManager = LinearLayoutManager(activity)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}