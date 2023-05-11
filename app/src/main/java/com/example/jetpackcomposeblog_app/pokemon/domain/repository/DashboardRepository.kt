package com.example.jetpackcomposeblog_app.pokemon.domain.repository

import com.example.jetpackcomposeblog_app.pokemon.data.Response
import com.example.jetpackcomposeblog_app.pokemon.data.constants.ApiConstant.PAGE_SIZE
import com.example.jetpackcomposeblog_app.pokemon.domain.model.Pokemon
import com.example.jetpackcomposeblog_app.pokemon.domain.model.PokemonList

interface DashboardRepository {
    suspend fun getPokemons(limit: Int = PAGE_SIZE, offset: Int = PAGE_SIZE): Response<PokemonList>

    suspend fun getPokemonByName(pokemonName: String): Response<Pokemon>
}