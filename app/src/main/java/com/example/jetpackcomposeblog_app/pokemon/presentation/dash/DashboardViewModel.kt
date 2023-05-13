package com.example.jetpackcomposeblog_app.pokemon.presentation.dash

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.jetpackcomposeblog_app.pokemon.data.Response
import com.example.jetpackcomposeblog_app.pokemon.data.constants.ApiConstant
import com.example.jetpackcomposeblog_app.pokemon.domain.model.PokemonData
import com.example.jetpackcomposeblog_app.pokemon.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val respository: DashboardRepository) : ViewModel() {

    private val _pokemonState = mutableStateOf<List<PokemonData>>(emptyList())
    val pokemonState: State<List<PokemonData>> = _pokemonState

    val isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    init {
        getPokemons()
    }

    fun getPokemons() {
        isLoading.value = true
        viewModelScope.launch {
            when (val result = respository.getPokemons()) {
                is Response.Success -> {
                    result.data?.results?.mapIndexed { _, item ->
                        val id = parseImageUrlForId(item.url)
                        PokemonData(id.toInt(), item.name, "${ApiConstant.POKEMON_IMAGE_URL}$id.png")
                    }?.let { pokemonData ->
                        _pokemonState.value += pokemonData
                    }
                    isLoading.value = false
                }

                is Response.Error -> {
                    loadError.value = result.message ?: "An unknown error occured"
                    isLoading.value = false
                }
            }
        }
    }

    private fun parseImageUrlForId(imageUrl: String) = if (imageUrl.endsWith("/")) {
        imageUrl.dropLast(1).takeLastWhile {
            it.isDigit()
        }
    } else {
        imageUrl.takeLastWhile { it.isDigit() }
    }

    fun imagePalette(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}