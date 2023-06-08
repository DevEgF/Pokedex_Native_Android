package com.example.pokedex.framework.repository

import androidx.paging.PagingSource
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.data.network.datasource.PokemonRemoteDataSource
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.framework.paging.PokemonPagingSource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
): PokemonRepository {

    override fun getPokemon(queries: String): PagingSource<Int, PokemonResult> {
        return PokemonPagingSource(remoteDataSource, queries)
    }
}