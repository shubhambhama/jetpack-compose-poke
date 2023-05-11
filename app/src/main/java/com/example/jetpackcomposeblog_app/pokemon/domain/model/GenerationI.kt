package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenerationI(
    @SerializedName("red-blue") val red_blue: RedBlue,
    val yellow: Yellow
)