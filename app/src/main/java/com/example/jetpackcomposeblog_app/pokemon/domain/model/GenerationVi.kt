package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire") val omegaruby_alphasapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y") val x_y: XY
)