package com.example.pokedex.data.repository

import com.example.pokedex.data.network.PokeApi
import com.example.pokedex.utils.BaseRepository
import javax.inject.Inject

class SinglePokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
): BaseRepository() {

    suspend fun getSinglePokemon(id: Int) = safeApiCall {
        pokeApi.getSinglePokemon(id)
    }
}