package com.example.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.framework.network.PokeApi
import com.example.pokedex.framework.network.response.PokemonResult
import com.example.pokedex.framework.paging.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi,
): PokemonRepository {
    override fun getPokemon(queries: String): Flow<PagingData<PokemonResult>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PokemonPagingSource(pokeApi, queries)
        }
    ).flow

    companion object {
        private const val PAGE_SIZE = 25
    }
}