package com.example.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokedex.framework.network.PokeApi
import com.example.pokedex.framework.paging.PokemonPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
): BaseRepository() {

    fun getPokemon(queries: String) = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
        pagingSourceFactory = {
            PokemonPagingSource(pokeApi, queries)
        }
    ).flow

    companion object{
        private const val PAGE_SIZE = 25
    }

}