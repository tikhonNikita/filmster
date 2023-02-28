package com.nikita.filmapp.theme.uiKit.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FilmImage(imageURL: String, modifier: Modifier = Modifier, size: Dp = 120.dp) {
    GlideImage(
        imageModel = { imageURL },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        ),
        requestOptions = {
            RequestOptions()
                .override(256, 256)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
        },
        modifier = modifier.size(size)
    )
}