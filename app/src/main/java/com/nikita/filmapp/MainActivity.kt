package com.nikita.filmapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.nikita.filmapp.adapter.FilmsAdapter
import com.nikita.filmapp.adapter.InteractionHandler
import com.nikita.filmapp.databinding.ActivityMainBinding
import com.nikita.filmapp.fragments.MainFragment
import com.nikita.filmapp.models.Film
import com.nikita.filmapp.models.filmLists
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmList: List<Film>
    private lateinit var favouriteFilms: MutableList<Film>
    private lateinit var startFavouriteActivity: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
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
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
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

    //
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        val data = Json.encodeToString(filmList)
//        outState.putString(FILMS_LIST, data)
//    }
//
//
//    private fun initRecyclerView(activityStarter: ActivityResultLauncher<Intent>) {
//        binding.rvFilmList.apply {
//            adapter = FilmsAdapter(filmList, object : InteractionHandler {
//                override fun handleClick(film: Film) {
//                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
//                    intent.putExtra(DetailsActivity.DATA_KEY, Json.encodeToString(film))
//                    activityStarter.launch(intent)
//                }
//
//                override fun addFilm(film: Film): Boolean {
//                    return if (favouriteFilms.contains(film)) {
//                        favouriteFilms.remove(film)
//                        false
//                    } else {
//                        favouriteFilms.add(film)
//                        true
//                    }
//                }
//
//                override fun checkIfInList(film: Film) = favouriteFilms.contains(film)
//
//
//            })
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//    }
//
    //
//    private fun goToFavouritesActivity() {
//        val intent = Intent(this@MainActivity, FavouriteActivity::class.java)
//        intent.putExtra(FAV_FILMS, Json.encodeToString(favouriteFilms))
//        startFavouriteActivity.launch(intent)
//    }
//
//
    companion object {
        const val DETAILS_RESULT = "DETAILS_RESULT"
        const val FILMS_LIST = "films"
        const val FAVOURITE_FILMS = "films" // from fav activity
        const val FILM_LIKED = "film_liked"
        const val COMMENTS = "comments"
        const val REQUEST_CODE = 321
        const val FAV_FILMS = "FAV_FILMS"
        const val DATA_KEY = "FILM_DATA_KEY"
    }
//
//    private fun initFilms(encodedFilms: String?): List<Film> {
//        return if (encodedFilms != null) {
//            Json.decodeFromString(encodedFilms)
//        } else {
//            listOf(
//                Film(1, "Batman", R.drawable.batman, "Film about batman", false),
//                Film(2, "Iron man 3", R.drawable.iron_man, filmLists[1].description, false),
//                Film(
//                    3,
//                    "Doctor Strange in the Multiverse of Madness",
//                    R.drawable.multiverse_of_madness,
//                    "Doctor Strange in the Multiverse of Madness film ",
//                    false
//                ),
//            )
//        }
//    }
}


//
//        filmList = initFilms(savedInstanceState?.getString(FILMS_LIST))
//        favouriteFilms = mutableListOf()
//
//        val startAnotherActivity = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            val data = result.data
//            if (result.resultCode == RESULT_OK && data != null) {
//                val liked = data.getBooleanExtra(FILM_LIKED, false)
//                if (data.hasExtra(COMMENTS)) {
//                    val comments = data.getStringExtra(COMMENTS)
//                    Toast.makeText(
//                        this,
//                        "Film ${if (liked) "is" else "not"} " + comments,
//                        Toast.LENGTH_LONG
//                    )
//                        .show()
//                }
//            }
//        }
//
//        startFavouriteActivity = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            val data = result.data
//            if (result.resultCode == RESULT_OK && data != null) {
//                if (data.hasExtra(FAVOURITE_FILMS)) {
//                    val rawFilms = data.getStringExtra(FAVOURITE_FILMS)
//                    if (rawFilms != null) {
//                        val films: MutableList<Film> = Json.decodeFromString(rawFilms)
//                        val filmsThatChanged = favouriteFilms.subtract(films)
//                        val adapter = binding.rvFilmList.adapter
//                        favouriteFilms = films
//                        filmsThatChanged.forEach { adapter?.notifyItemChanged(filmList.indexOf(it)) }
//                    }
//                }
//            }
//        }
//
//
//        val view = binding.root
//        setContentView(view)
//        initRecyclerView(startAnotherActivity)