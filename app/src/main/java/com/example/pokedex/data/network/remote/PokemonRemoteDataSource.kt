package com.example.pokedex.data.network.remote

import com.example.pokedex.data.network.domain.PokemonResponse
import com.example.pokedex.data.network.domain.SinglePokemonResponse

interface PokemonRemoteDataSource {

    suspend fun fetchPokemon(limit: Int, offset: Int): PokemonResponse
}