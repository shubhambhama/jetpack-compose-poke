package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenerationIii(
    val emerald: Emerald,
    @SerializedName("firered-leafgreen") val firered_leafgreen: FireredLeafgreen,
    @SerializedName("ruby-sapphire") val ruby_sapphire: RubySapphire
)