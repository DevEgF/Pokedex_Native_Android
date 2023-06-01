package com.example.pokedex.data.network.remote

import com.example.pokedex.data.network.PokeApi
import com.example.pokedex.data.network.domain.PokemonResponse
import com.example.pokedex.data.network.domain.PokemonResult
import javax.inject.Inject

class RetrofitPokemonDataSource @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRemoteDataSource{
    override suspend fun fetchPokemon(limit: Int, offset: Int): PokemonResponse {
        val count = pokeApi.getPokemons(limit, offset).count
        val next = pokeApi.getPokemons(limit, offset).next
        val previous = pokeApi.getPokemons(limit, offset).previous
        val results = pokeApi.getPokemons(limit, offset).results
        return PokemonResponse(count, next, previous, results)
    }
}