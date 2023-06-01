package com.example.pokedex.data.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.data.network.domain.SinglePokemonResponse
import com.example.pokedex.utils.base.NetworkResource
import kotlinx.coroutines.flow.Flow
 interface PokemonRepository {
    fun getPokemon(queries: String): PagingSource<Int, PokemonResult>

   suspend fun getSinglePokemon(id: Int): NetworkResource<SinglePokemonResponse>
 }