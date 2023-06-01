package com.example.pokedex.data.network.remote

import com.example.pokedex.data.network.domain.PokemonResponse

interface PokemonRemoteDataSource {

    suspend fun fetchPokemon(limit: Int, offset: Int): PokemonResponse
}