package com.example.jetpackcomposeblog_app.pokemon.data.datasource

import com.example.jetpackcomposeblog_app.pokemon.data.constants.ApiConstant.PAGE_SIZE
import com.example.jetpackcomposeblog_app.pokemon.domain.model.Pokemon
import com.example.jetpackcomposeblog_app.pokemon.domain.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int = PAGE_SIZE, @Query("offset") offset: Int = PAGE_SIZE): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Pokemon
}