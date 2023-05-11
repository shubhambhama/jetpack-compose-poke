package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork") val official_artwork: OfficialArtwork
)