package com.example.pokedex.framework.local

import com.example.pokedex.data.datasource.FavoriteLocalDataSource
import com.example.pokedex.domain.model.PokemonResult
import com.example.pokedex.framework.db.dao.FavoriteDao
import com.example.pokedex.framework.db.entity.FavoriteEntity
import com.example.pokedex.framework.db.entity.toPokemonResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
): FavoriteLocalDataSource {

    override fun getAll(): Flow<List<PokemonResult>> {
        return favoriteDao.loadFavorites().map {
            it.toPokemonResultModel()
        }
    }

    override suspend fun save(pokemonResult: PokemonResult) {
        favoriteDao.insertFavorite(pokemonResult.toFavoriteEntity())
    }

    override suspend fun delete(pokemonResult: PokemonResult) {
        favoriteDao.deleteFavorite(pokemonResult.toFavoriteEntity())
    }

    private fun PokemonResult.toFavoriteEntity()= FavoriteEntity(name, url)
}