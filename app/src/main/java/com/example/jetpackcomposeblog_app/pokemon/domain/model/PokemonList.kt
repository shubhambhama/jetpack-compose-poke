package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)