package com.example.pokedex.data.repository

import com.example.pokedex.data.network.domain.PokemonResult
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getAll(): Flow<List<PokemonResult>>

    suspend fun saveFavorite(pokemonResult: PokemonResult)

    suspend fun deleteFavorite(pokemonResult: PokemonResult)
}