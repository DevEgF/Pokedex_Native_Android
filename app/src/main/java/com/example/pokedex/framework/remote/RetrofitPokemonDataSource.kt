package com.example.pokedex.framework.remote

import com.example.pokedex.data.network.PokeApi
import com.example.pokedex.data.network.domain.PokemonResponse
import com.example.pokedex.data.network.domain.SinglePokemonResponse
import com.example.pokedex.data.network.datasource.PokemonRemoteDataSource
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

    override suspend fun fetchSinglePokemon(id: Int): SinglePokemonResponse {
        val sprites = pokeApi.getSinglePokemon(id).sprites
        val stats = pokeApi.getSinglePokemon(id).stats
        val height = pokeApi.getSinglePokemon(id).height
        val weight = pokeApi.getSinglePokemon(id).weight
        val id = pokeApi.getSinglePokemon(id).id
        val name = pokeApi.getSinglePokemon(id).name
        return SinglePokemonResponse(sprites, stats, height, weight, id, name)
    }
}