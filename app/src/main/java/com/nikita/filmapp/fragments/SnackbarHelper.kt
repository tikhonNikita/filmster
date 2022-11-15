package com.nikita.filmapp.fragments

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackbarHelper {
    companion object {
        fun showSnackbar(
            view: View,
            message: String,
            anchorView: View?
        ) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
                .setAnchorView(anchorView ?: view)
                .show()
        }
    }
}