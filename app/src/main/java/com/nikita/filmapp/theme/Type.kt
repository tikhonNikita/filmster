/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nikita.filmapp.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nikita.filmapp.R

private val Lato = FontFamily(
    Font(R.font.lato_black),
    Font(R.font.lato_black_italic),
    Font(R.font.lato_bold),
    Font(R.font.lato_black_italic),
    Font(R.font.lato_italic),
    Font(R.font.lato_light),
    Font(R.font.lato_light_italic),
    Font(R.font.lato_regular),
    Font(R.font.lato_thin),
    Font(R.font.lato_thin_italic)
)


val FilmAppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Lato,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)
