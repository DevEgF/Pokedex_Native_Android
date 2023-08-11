package com.example.pokedex.data.repository

import com.example.pokedex.domain.model.SinglePokemon

interface SinglePokemonRepository {

    suspend fun getSinglePokemon(id: Int): SinglePokemon
}