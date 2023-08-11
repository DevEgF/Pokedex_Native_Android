package com.example.pokedex.data.datasource

import com.example.pokedex.data.dto.PokemonResponse
import com.example.pokedex.domain.model.SinglePokemon

interface PokemonRemoteDataSource {

    suspend fun fetchPokemon(limit: Int, offset: Int): PokemonResponse

    suspend fun fetchSinglePokemon(id: Int): SinglePokemon
}