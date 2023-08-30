package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.model.PokemonResult
import com.example.pokedex.data.repository.FavoritesRepository
import com.example.pokedex.utils.AppCoroutinesDispatchers
import com.example.pokedex.utils.ResultStatus
import com.example.pokedex.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddFavoriteUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val name: String, val url:String)
}

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoritesRepository,
    private val dispatchers: AppCoroutinesDispatchers
): AddFavoriteUseCase, UseCase<AddFavoriteUseCase.Params, Unit>(){

    override suspend fun doWork(params: AddFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()){
            favoriteRepository.saveFavorite(
                PokemonResult(params.name, params.url)
            )
            ResultStatus.Success(Unit)
        }
    }
}