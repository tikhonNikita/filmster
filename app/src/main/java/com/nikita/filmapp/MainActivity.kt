package com.nikita.filmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nikita.filmapp.fragments.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       goToMainFragment()
//        val navigate: BottomNavigationView = findViewById(R.id.navigate)
    }

        private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, MainFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
