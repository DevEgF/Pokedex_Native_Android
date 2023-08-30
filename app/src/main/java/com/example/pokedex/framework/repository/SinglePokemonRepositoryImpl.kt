package com.example.pokedex.framework.repository

import com.example.pokedex.data.datasource.PokemonRemoteDataSource
import com.example.pokedex.domain.model.SinglePokemon
import com.example.pokedex.data.repository.SinglePokemonRepository
import javax.inject.Inject

class SinglePokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
): SinglePokemonRepository {

    override suspend fun getSinglePokemon(id: Int): SinglePokemon {
        return remoteDataSource.fetchSinglePokemon(id)
    }
}