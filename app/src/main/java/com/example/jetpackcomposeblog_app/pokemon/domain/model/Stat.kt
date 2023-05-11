package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)