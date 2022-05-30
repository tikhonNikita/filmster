package com.nikita.filmapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.filmapp.DetailsActivity
import com.nikita.filmapp.MainActivity
import com.nikita.filmapp.R
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.adapter.InteractionHandler
import com.nikita.filmapp.databinding.MainFragmentBinding
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.filmLists
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private lateinit var filmList: List<Film>

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmList = initFilms(savedInstanceState?.getString(MainActivity.FILMS_LIST))
        initRecyclerView()
    }

    private fun initFilms(encodedFilms: String?): List<Film> {
        return if (encodedFilms != null) {
            Json.decodeFromString(encodedFilms)
        } else {
            listOf(
                Film(1, "Batman", R.drawable.batman, "Film about batman", false),
                Film(2, "Iron man 3", R.drawable.iron_man, filmLists[1].description, false),
                Film(
                    3,
                    "Doctor Strange in the Multiverse of Madness",
                    R.drawable.multiverse_of_madness,
                    "Doctor Strange in the Multiverse of Madness film ",
                    false
                ),
            )
        }
    }

    private fun initRecyclerView() {
        binding.rvFilmList.apply {
            adapter = FilmsAdapter(filmList, object : InteractionHandler {
                override fun handleClick(film: Film) {
                    val stringedFilm = Json.encodeToString(film)
                    val fragmentToGo = DetailsFragment()
                    val args = Bundle().apply {
                        putString(MainActivity.DATA_KEY, stringedFilm)
                    }
                    fragmentToGo.arguments = args
                    activity?.apply {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, fragmentToGo)
                            .remove(this@MainFragment)
                            .addToBackStack("go_to_details")
                            .commit()
                    }
//                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
//                    intent.putExtra(DetailsActivity.DATA_KEY, Json.encodeToString(film))
//                    activityStarter.launch(intent)
                }

                override fun addFilm(film: Film): Boolean = true
                //{
//                    return if (favouriteFilms.contains(film)) {
//                        favouriteFilms.remove(film)
//                        false
//                    } else {
//                        favouriteFilms.add(film)
//                        true
//                    }
                //   true
                // }

                override fun checkIfInList(film: Film) = true


            })
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}