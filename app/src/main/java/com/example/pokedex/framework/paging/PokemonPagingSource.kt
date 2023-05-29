package com.example.pokedex.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.framework.network.PokeApi
import com.example.pokedex.framework.network.response.PokemonResult
import java.io.IOException

@Suppress("TooGenericExceptionCaught","InstanceOfCheckForException")
class PokemonPagingSource(
    private val pokeApi: PokeApi,
    private val queries: String
): PagingSource<Int, PokemonResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val offset = params.key ?: START_OFFSET

        val loadSize = LOAD_SIZE

        return try {
            val data = pokeApi.getPokemons(loadSize,offset)

            val filteredData = data.results.filter {
                it.name.contains(queries, true)
            }

            LoadResult.Page(
                data = filteredData,
                prevKey = if(offset == START_OFFSET) null else offset - loadSize,
                nextKey = if(data.next == null) null else offset + loadSize
            )
        } catch (t: Throwable) {
            var expection = t

            if(t is IOException){
                expection = IOException("Please check internet connection")
            }
            LoadResult.Error(expection)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition
    }

    companion object{
        private const val START_OFFSET = 0
        private const val LOAD_SIZE = 100
    }
}