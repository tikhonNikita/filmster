package com.nikita.filmapp.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.models.Film
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
            it.forEach{
                Log.d("SEKSON", it.title)
            }
        }


        //TODO: make newtwork request here
        val handler = Handler()
        handler.postDelayed(Runnable {
            binding.laLoader.visibility = View.INVISIBLE

            binding.rvFilmList.visibility = View.VISIBLE

        }, 2000)

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
//        updateData()
    }

//    private fun updateData() {
//        viewModel.updateFilms()
//    }

    private fun initRecyclerView(list: List<Film>) {
        binding.rvFilmList.apply {
            adapter = FilmsAdapter(list, activity as MainActivity) {
//                updateData()
            }
            layoutManager = LinearLayoutManager(activity)
        }
    }

//    private fun updateRecyclerView() {
//        binding.rvFilmList.adapter = viewModel.filmL.value?.let {
//            FilmsAdapter(it, activity as MainActivity) {
////                updateData()
//            }
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}