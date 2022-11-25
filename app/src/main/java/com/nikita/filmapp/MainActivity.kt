package com.nikita.filmapp

import MovieViewModelFactory
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nikita.filmapp.db.MovieDataBase
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

    public fun goToDetailsFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_mainFragment_to_detailsFragment)
    }

}

