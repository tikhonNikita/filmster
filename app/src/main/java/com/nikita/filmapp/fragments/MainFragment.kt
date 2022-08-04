package com.nikita.filmapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.models.filmLists


private const val TAG = "MAIN_FRAGMENT"
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun initRecyclerView() {
        binding?.rvFilmList?.apply {
            adapter = FilmsAdapter(filmLists, activity as MainActivity)
            layoutManager = LinearLayoutManager(activity)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}