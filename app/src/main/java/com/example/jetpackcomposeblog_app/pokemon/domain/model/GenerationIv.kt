package com.example.jetpackcomposeblog_app.pokemon.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenerationIv(
    @SerializedName("diamond-pearl") val diamond_pearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver") val heartgold_soulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)