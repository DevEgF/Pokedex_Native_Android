package com.example.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.data.repository.base.BaseRepository
import com.example.pokedex.framework.network.PokeApi
import com.example.pokedex.framework.network.response.PokemonResult
import com.example.pokedex.framework.paging.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(private val pokeApi: PokeApi) : BaseRepository()  {

    fun getPokemon(queries: String): Flow<PagingData<PokemonResult>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PokemonPagingSource(pokeApi, queries)
        }
    ).flow

    suspend fun getSinglePokemon(id: Int) = safeApiCall {
        pokeApi.getSinglePokemon(id)
    }

    companion object {
        private const val PAGE_SIZE = 25
    }
}