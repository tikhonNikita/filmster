package com.nikita.filmapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.filmLists
import com.nikita.filmapp.viewModels.FilmViewModel
import kotlin.math.log


private const val TAG = "MAIN_SOBAKA_FRAGMENT"

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding

    private val viewModel: FilmViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()
        viewModel.filmL.observe(viewLifecycleOwner) {
            Log.d(TAG, viewModel.filmL.value.toString())
            updateRecyclerView()
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(listOf())
        updateData()
    }

    private fun updateData() {
        viewModel.updateFilms()
    }

    private fun initRecyclerView(list: List<Film>) {
        binding?.rvFilmList?.apply {
            adapter = FilmsAdapter(list, activity as MainActivity) {
                updateData()
            }
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun updateRecyclerView() {
        binding!!.rvFilmList.adapter = viewModel.filmL.value?.let {
            FilmsAdapter(it, activity as MainActivity) {
                updateData()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}