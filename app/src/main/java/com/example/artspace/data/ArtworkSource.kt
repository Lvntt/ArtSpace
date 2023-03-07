package com.example.artspace.data

import com.example.artspace.R

object ArtworkSource {

    private val artworks = listOf(
        Artwork(
            R.drawable.vincent_van_gogh_self_portrait,
            "Self-Portrait",
            "Vincent van Gogh",
            1889
        ),
        Artwork(
            R.drawable.george_stubbs_white_poodle_in_a_punt,
            "White Poodle in a Punt",
            "George Stubbs",
            1780
        ),
        Artwork(
            R.drawable.edouard_vuillard_place_vintimille,
            "Place Vintimille",
            "Edouard Vuillard",
            1911
        ),
        Artwork(
            R.drawable.claude_monet_the_japanese_footbridge,
            "The Japanese Footbridge",
            "Claude Monet",
            1899
        ),
        Artwork(
            R.drawable.gilbert_stuart_eleanor_parke_custis_lewis,
            "Eleanor Parke Custis Lewis",
            "Gilbert Stuart",
            1804
        ),
    )

    fun getArtworks(): List<Artwork> = artworks
}