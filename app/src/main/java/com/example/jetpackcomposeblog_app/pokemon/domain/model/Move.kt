package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep

@Keep
data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)