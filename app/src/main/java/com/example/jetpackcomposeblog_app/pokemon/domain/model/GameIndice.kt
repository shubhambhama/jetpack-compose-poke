package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class GameIndice(
    val game_index: Int,
    val version: Version
)