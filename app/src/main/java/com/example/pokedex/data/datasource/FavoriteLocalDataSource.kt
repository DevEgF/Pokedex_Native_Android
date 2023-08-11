package com.example.pokedex.data.datasource

import com.example.pokedex.domain.model.PokemonResult
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    fun getAll(): Flow<List<PokemonResult>>

    suspend fun save(pokemonResult: PokemonResult)

    suspend fun delete(pokemonResult: PokemonResult)
}