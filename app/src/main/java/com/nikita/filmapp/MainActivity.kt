package com.nikita.filmapp

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nikita.filmapp.databinding.ActivityMainBinding
import com.nikita.filmapp.fragments.FavouritesFragment
import com.nikita.filmapp.fragments.FavouritesFragment.Companion.FAV_FRAGMENT_DATA
import com.nikita.filmapp.fragments.FavouritesFragment.Companion.FAV_FRAGMENT_RESULT
import com.nikita.filmapp.fragments.FilmsHandler
import com.nikita.filmapp.fragments.MainFragment
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.filmLists
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity(), FilmsHandler {

    private lateinit var binding: ActivityMainBinding
    override lateinit var films: MutableList<Film>
    override lateinit var favouriteFilms: MutableList<Film>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        films = initFilms(savedInstanceState?.getString(FILMS_LIST))
        favouriteFilms = initFavFilms(savedInstanceState?.getString(FAV_FILM_LIST))
        setContentView(view)
        goToMainFragment()
        initDrawer()
    }

    private fun initDrawer() {
        setSupportActionBar(binding.mainToolbar)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.mainToolbar,
            R.string.app_name,
            R.string.batman
        )


        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dmAll -> {
                    goToMainFragment()
                    binding.drawerLayout.closeDrawers()
                    true
                }
                R.id.dmFavourites -> {
                    goToFavouritesFragment()
                    binding.drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    true
                }
            }
        }
        supportFragmentManager.addOnBackStackChangedListener {
            val fragments = supportFragmentManager.fragments
            if (fragments[0] is MainFragment) {
                binding.navigationView.menu.getItem(0).isChecked = true

            }
        }
    }

    private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
    }


    private fun goToFavouritesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FavouritesFragment.create(favouriteFilms))
            .addToBackStack(null)
            .commit()
        supportFragmentManager.setFragmentResultListener(FAV_FRAGMENT_RESULT, this) { _, bundle ->
            val updatedFavourites = bundle.getString(FAV_FRAGMENT_DATA)
            updatedFavourites?.also {
                val newFavFilms: MutableList<Film> = Json.decodeFromString(it)
                favouriteFilms = newFavFilms
            }
        }
    }

    //
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return
        }
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNegativeButton(resources.getString(R.string.decline)) { _, _ -> }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                super.onBackPressed()
            }
            .show()

    }

    override fun addToFavourites(film: Film) = favouriteFilms.add(film)

    override fun removeFromFavourites(film: Film) = favouriteFilms.remove(film)

    override fun checkIfInFavourites(film: Film) = favouriteFilms.contains(film)

    //
//
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val data = Json.encodeToString(films)
        val favData = Json.encodeToString(favouriteFilms)
        outState.putString(FILMS_LIST, data)
        outState.putString(FAV_FILM_LIST, favData)
    }

    companion object {
        const val DETAILS_RESULT = "DETAILS_RESULT"
        const val FILMS_LIST = "films"
        const val FAV_FILM_LIST = "films_fav"
        const val FAV_FILMS = "FAV_FILMS"
        const val DATA_KEY = "FILM_DATA_KEY"
    }

    //
    private fun initFilms(encodedFilms: String?): MutableList<Film> {
        return if (encodedFilms != null) {
            Json.decodeFromString(encodedFilms)
        } else {
            mutableListOf(
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

    private fun initFavFilms(encodedFilms: String?): MutableList<Film> {
        return if (encodedFilms != null) {
            Json.decodeFromString(encodedFilms)
        } else {
            mutableListOf()
        }
    }
}
