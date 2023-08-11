package com.example.pokedex.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedex.domain.model.PokemonResult
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.usecase.GetPokemonUseCase.GetPokemonParams
import com.example.pokedex.utils.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPokemonUseCase {
    operator fun invoke(
        params: GetPokemonParams
    ): Flow<PagingData<PokemonResult>>

    data class GetPokemonParams(val query: String, val pagingConfig: PagingConfig)
}

class GetPokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
): PagingUseCase<GetPokemonParams, PokemonResult>(), GetPokemonUseCase {

    override fun createFlowObservable(params: GetPokemonParams): Flow<PagingData<PokemonResult>> {
        val pagingSource= pokemonRepository.getPokemon(params.query)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}