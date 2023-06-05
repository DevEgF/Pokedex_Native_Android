package com.example.pokedex.data.usecase

import com.example.pokedex.data.network.domain.SinglePokemonResponse
import com.example.pokedex.data.repository.SinglePokemonRepository
import com.example.pokedex.data.usecase.base.AppCoroutinesDispatchers
import com.example.pokedex.data.usecase.base.ResultStatus
import com.example.pokedex.data.usecase.base.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetSinglePokemonUseCase {

    operator fun invoke (
        params: GetSinglePokemonParams
    ): Flow<ResultStatus<SinglePokemonResponse>>

    data class GetSinglePokemonParams(val id: Int)
}

class GetSinglePokemonUseCaseImpl @Inject constructor(
    private val singlePokemonRepository: SinglePokemonRepository,
    private val dispatchers: AppCoroutinesDispatchers
): GetSinglePokemonUseCase, UseCase<GetSinglePokemonUseCase.GetSinglePokemonParams, SinglePokemonResponse>(){

    override suspend fun doWork(params: GetSinglePokemonUseCase.GetSinglePokemonParams): ResultStatus<SinglePokemonResponse> {
        return withContext(dispatchers.io()){
            val singlePokemonDeferred = async {
                singlePokemonRepository.getSinglePokemon(params.id)
            }

            val singlePokemon = singlePokemonDeferred.await()

            ResultStatus.Success(singlePokemon)
        }
    }
}