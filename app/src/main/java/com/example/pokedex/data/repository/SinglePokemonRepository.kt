package com.example.pokedex.data.repository

import com.example.pokedex.data.network.domain.SinglePokemonResponse

interface SinglePokemonRepository {

    suspend fun getSinglePokemon(id: Int): SinglePokemonResponse
}