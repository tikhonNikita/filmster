package com.nikita.filmapp.compose.favourites


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.codelab.theming.ui.start.theme.FilmAppShapes
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FavouriteItem(title: String, imageURL: String, modifier: Modifier = Modifier) {
    return Surface(shape = MaterialTheme.shapes.small, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .width(256.dp).background(MaterialTheme.colorScheme.surface)
        ) {
            Box(modifier = Modifier.clip(FilmAppShapes.large).size(120.dp)) {
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
                    modifier = Modifier
                        .size(120.dp)
                )
            }
            FavouriteTitle(title = title)
        }
    }


}