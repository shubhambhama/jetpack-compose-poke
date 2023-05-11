package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class Type(
    val slot: Int,
    val type: TypeX
)