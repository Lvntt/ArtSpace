package com.example.artspace.data

import androidx.annotation.DrawableRes

data class Artwork(
    @DrawableRes val imageId: Int,
    val title: String,
    val artist: String,
    val year: Int
)
