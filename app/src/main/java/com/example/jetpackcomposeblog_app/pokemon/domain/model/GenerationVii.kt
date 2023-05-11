package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenerationVii(
    val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon") val ultra_sun_ultra_moon: UltraSunUltraMoon
)