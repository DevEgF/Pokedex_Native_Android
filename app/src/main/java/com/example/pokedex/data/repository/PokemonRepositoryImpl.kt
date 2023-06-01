package com.example.pokedex.data.repository

import androidx.paging.PagingSource
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.data.network.domain.SinglePokemonResponse
import com.example.pokedex.data.network.remote.PokemonRemoteDataSource
import com.example.pokedex.framework.paging.PokemonPagingSource
import com.example.pokedex.utils.base.NetworkResource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
): PokemonRepository {

    override fun getPokemon(queries: String): PagingSource<Int, PokemonResult> {
        return PokemonPagingSource(remoteDataSource, queries)
    }

    override suspend fun getSinglePokemon(id: Int): NetworkResource<SinglePokemonResponse> {
        TODO("Not yet implemented")
    }
}