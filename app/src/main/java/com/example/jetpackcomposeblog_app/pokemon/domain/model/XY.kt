package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class XY(
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)