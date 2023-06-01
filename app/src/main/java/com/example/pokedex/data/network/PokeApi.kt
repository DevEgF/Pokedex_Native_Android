package com.example.pokedex.data.network

import com.example.pokedex.data.network.domain.PokemonResponse
import com.example.pokedex.data.network.domain.SinglePokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): PokemonResponse

    @GET("pokemon/{id}/")
    suspend fun getSinglePokemon(
        @Path("id") id: Int
    ): SinglePokemonResponse
}