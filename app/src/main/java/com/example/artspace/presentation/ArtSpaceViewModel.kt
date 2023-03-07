package com.example.artspace.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.artspace.data.Artwork
import com.example.artspace.data.ArtworkSource

class ArtSpaceViewModel : ViewModel() {

    private val artworks = ArtworkSource.getArtworks()
    private var currentArtworkIndex = 0

    private val _currentArtwork = mutableStateOf(artworks[currentArtworkIndex])
    val currentArtwork: Artwork
        get() = _currentArtwork.value

    fun goToNextArtwork() {
        currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
        _currentArtwork.value = artworks[currentArtworkIndex]
    }

    fun goToPreviousArtwork() {
        currentArtworkIndex = if (currentArtworkIndex - 1 < 0) {
            artworks.lastIndex
        } else {
            (currentArtworkIndex - 1) % artworks.size
        }
        _currentArtwork.value = artworks[currentArtworkIndex]
    }
}