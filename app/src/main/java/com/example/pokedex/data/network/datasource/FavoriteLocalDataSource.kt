package com.example.pokedex.data.network.datasource

import com.example.pokedex.data.network.domain.PokemonResult
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    fun getAll(): Flow<List<PokemonResult>>

    suspend fun save(pokemonResult: PokemonResult)

    suspend fun delete(pokemonResult: PokemonResult)
}