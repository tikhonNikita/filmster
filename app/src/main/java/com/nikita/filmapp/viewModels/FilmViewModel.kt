package com.nikita.filmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilmViewModel : ViewModel() {
    private val myTest = MutableLiveData<String>()

    val test: LiveData<String>
        get() = myTest

    fun testPost() {
        myTest.postValue("Some test value")
    }
}