package com.example.pokedex.framework.repository

import com.example.pokedex.domain.model.PokemonResult
import com.example.pokedex.data.datasource.FavoriteLocalDataSource
import com.example.pokedex.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource
): FavoritesRepository {

    override fun getAll(): Flow<List<PokemonResult>> {
        return favoriteLocalDataSource.getAll()
    }

    override suspend fun saveFavorite(pokemonResult: PokemonResult) {
        return favoriteLocalDataSource.save(pokemonResult)
    }

    override suspend fun deleteFavorite(pokemonResult: PokemonResult) {
        return favoriteLocalDataSource.delete(pokemonResult)
    }
}