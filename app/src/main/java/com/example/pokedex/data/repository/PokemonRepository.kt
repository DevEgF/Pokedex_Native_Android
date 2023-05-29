package com.example.pokedex.data.repository

import androidx.paging.PagingData
import com.example.pokedex.framework.network.response.PokemonResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemon(queries: String): Flow<PagingData<PokemonResult>>
}