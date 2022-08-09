package com.nikita.filmapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.models.filmLists
import com.nikita.filmapp.viewModels.FilmViewModel


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
        Log.d(TAG, "onCreateView: ")
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.show()
        viewModel.test.observe(viewLifecycleOwner) {
            Log.d(TAG, "initRecyclerView: $it")
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.testPost()
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