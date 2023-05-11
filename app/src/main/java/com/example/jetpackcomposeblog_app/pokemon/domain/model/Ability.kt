package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)