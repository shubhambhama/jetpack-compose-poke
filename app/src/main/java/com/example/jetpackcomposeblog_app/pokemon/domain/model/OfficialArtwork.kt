package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String
)