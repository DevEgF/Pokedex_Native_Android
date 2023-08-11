package com.example.pokedex.framework.remote

import com.example.pokedex.service.PokeApi
import com.example.pokedex.data.dto.PokemonResponse
import com.example.pokedex.data.datasource.PokemonRemoteDataSource
import com.example.pokedex.domain.model.SinglePokemon
import com.example.pokedex.data.dto.toSinglePokemonModel
import javax.inject.Inject

class RetrofitPokemonDataSource @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRemoteDataSource {
    override suspend fun fetchPokemon(limit: Int, offset: Int): PokemonResponse {
        val count = pokeApi.getPokemons(limit, offset).count
        val next = pokeApi.getPokemons(limit, offset).next
        val previous = pokeApi.getPokemons(limit, offset).previous
        val results = pokeApi.getPokemons(limit, offset).results
        return PokemonResponse(count, next, previous, results)
    }

    override suspend fun fetchSinglePokemon(id: Int): SinglePokemon {
        return pokeApi.getSinglePokemon(id).toSinglePokemonModel()
    }
}