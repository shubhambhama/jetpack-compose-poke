package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class VersionGroup(
    val name: String,
    val url: String
)