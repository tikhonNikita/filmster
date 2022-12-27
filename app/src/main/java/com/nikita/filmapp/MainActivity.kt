package com.nikita.filmapp

import MovieViewModelFactory
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.filmapp.db.MovieDataBase
import com.nikita.filmapp.fragments.MainFragmentDirections
import com.nikita.filmapp.repository.MoviesRepository
import com.nikita.filmapp.viewModels.MovieDetailsViewModelFactory
import com.nikita.filmapp.viewModels.MovieViewModel


/*
  TODO:     1) Add ability to see details of the movies
            2) Поиск по тайтлу (лоу приорити)


 */
class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: MovieViewModelFactory
    lateinit var movieDetailsViewModelFactory: MovieDetailsViewModelFactory
    private val viewModel: MovieViewModel by viewModels { this.viewModelFactory }

    private lateinit var moviesRepository: MoviesRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH)

        //TODO: handle nav fragment
        moviesRepository = MoviesRepository(MovieDataBase(this))

        viewModelFactory = MovieViewModelFactory(moviesRepository)
        movieDetailsViewModelFactory = MovieDetailsViewModelFactory(moviesRepository)
        val navigationView: BottomNavigationView = findViewById(R.id.navigate)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    public fun goToDetailsFragment(movieID: Long, posterURL: String, view1: View) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val extras = FragmentNavigatorExtras(view1 to "posterTransition")
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(
            movieID = movieID,
            posterURL = posterURL
        )
        navController.navigate(action, extras)
    }

}

