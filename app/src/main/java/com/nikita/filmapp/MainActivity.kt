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
import com.nikita.filmapp.viewModels.MovieViewModel


/*
    TODO: 1) Add UI in indication to favourites
          2) Add ability to remove movies from favourites
          0) Make card look like https://m2.material.io/components/cards/android#card

 */
class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: MovieViewModelFactory
    private val viewModel: MovieViewModel by viewModels { this.viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: handle nav fragment

        viewModelFactory = MovieViewModelFactory(MoviesRepository(MovieDataBase(this)))
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

