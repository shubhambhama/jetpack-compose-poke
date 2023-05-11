package com.example.jetpackcomposeblog_app.pokemon.data.repository

import com.example.jetpackcomposeblog_app.pokemon.data.Response
import com.example.jetpackcomposeblog_app.pokemon.data.constants.ApiConstant.PAGE_SIZE
import com.example.jetpackcomposeblog_app.pokemon.data.datasource.ApiService
import com.example.jetpackcomposeblog_app.pokemon.domain.model.Pokemon
import com.example.jetpackcomposeblog_app.pokemon.domain.model.PokemonList
import com.example.jetpackcomposeblog_app.pokemon.domain.repository.DashboardRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DashboardRepositoryImpl @Inject constructor(private val apiService: ApiService): DashboardRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Response<PokemonList> {
        val response = try {
            apiService.getPokemons(limit, offset)
        } catch (e: Exception) {
            return Response.Error("An unknown error occured")
        }
        return Response.Success(response)
    }

    override suspend fun getPokemonByName(pokemonName: String): Response<Pokemon> {
        val response = try {
            apiService.getPokemonByName(pokemonName)
        } catch (e: Exception) {
            return Response.Error("An unknown error occured")
        }
        return Response.Success(response)
    }
}