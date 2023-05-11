package com.example.jetpackcomposeblog_app.pokemon.presentation.dash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            when (val result = respository.getPokemons()) {
                is Response.Success -> {
                    result.data?.results?.mapIndexed { _, item ->
                        val id = parseImageUrlForId(item.url)
                        PokemonData(id.toInt(), item.name, "${ApiConstant.POKEMON_IMAGE_URL}$id.png")
                    }?.let { pokemonData ->
                        _pokemonState.value += pokemonData
                    }
                }

                is Response.Error -> {

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
}