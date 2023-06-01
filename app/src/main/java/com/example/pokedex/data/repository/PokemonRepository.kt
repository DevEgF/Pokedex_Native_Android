package com.example.pokedex.data.repository

import androidx.paging.PagingData
import com.example.pokedex.framework.network.response.PokemonResult
import com.example.pokedex.framework.network.response.SinglePokemonResponse
import com.example.pokedex.utils.base.NetworkResource
import kotlinx.coroutines.flow.Flow
 interface PokemonRepository {
    fun getPokemon(queries: String): Flow<PagingData<PokemonResult>>

   suspend fun getSinglePokemon(id: Int): NetworkResource<SinglePokemonResponse>
 }