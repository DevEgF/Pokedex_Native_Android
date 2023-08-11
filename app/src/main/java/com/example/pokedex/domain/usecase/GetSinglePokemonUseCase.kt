package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.model.SinglePokemon
import com.example.pokedex.data.repository.SinglePokemonRepository
import com.example.pokedex.utils.AppCoroutinesDispatchers
import com.example.pokedex.utils.ResultStatus
import com.example.pokedex.utils.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetSinglePokemonUseCase {

    operator fun invoke(
        params: GetSinglePokemonParams
    ): Flow<ResultStatus<SinglePokemon>>

    data class GetSinglePokemonParams(val id: Int)
}

class GetSinglePokemonUseCaseImpl @Inject constructor(
    private val singlePokemonRepository: SinglePokemonRepository,
    private val dispatchers: AppCoroutinesDispatchers
) : GetSinglePokemonUseCase,
    UseCase<GetSinglePokemonUseCase.GetSinglePokemonParams, SinglePokemon>() {

    override suspend fun doWork(
        params: GetSinglePokemonUseCase.GetSinglePokemonParams
    ): ResultStatus<SinglePokemon> {
        return withContext(dispatchers.io()) {
            val singlePokemonDeferred = async {
                singlePokemonRepository.getSinglePokemon(params.id)
            }

            val singlePokemon = singlePokemonDeferred.await()

            ResultStatus.Success(singlePokemon)
        }
    }
}