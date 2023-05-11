package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class PokemonData(val id: Int, val name: String, val imageUrl: String)
