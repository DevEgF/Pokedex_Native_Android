package com.example.pokedex.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.data.datasource.PokemonRemoteDataSource
import com.example.pokedex.domain.model.PokemonResult

@Suppress("TooGenericExceptionCaught")
class PokemonPagingSource(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val queries: String
) : PagingSource<Int, PokemonResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val offset = params.key ?: START_OFFSET

        val loadSize = LOAD_SIZE

        return try {
            val data = remoteDataSource.fetchPokemon(loadSize, offset)
            val filteredData = data.results.filter { it.name.contains(queries, true) }

            LoadResult.Page(
                data = filteredData,
                prevKey = if (offset == START_OFFSET) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val START_OFFSET = 0
        private const val LIMIT = 20
        private const val LOAD_SIZE = 100
    }
}