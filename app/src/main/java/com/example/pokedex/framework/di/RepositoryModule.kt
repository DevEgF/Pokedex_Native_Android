package com.example.pokedex.framework.di

import com.example.pokedex.data.network.datasource.FavoriteLocalDataSource
import com.example.pokedex.data.network.datasource.PokemonRemoteDataSource
import com.example.pokedex.data.repository.FavoritesRepository
import com.example.pokedex.framework.remote.RetrofitPokemonDataSource
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.framework.repository.PokemonRepositoryImpl
import com.example.pokedex.data.repository.SinglePokemonRepository
import com.example.pokedex.framework.local.RoomFavoritesDataSource
import com.example.pokedex.framework.repository.FavoriteRepositoryImpl
import com.example.pokedex.framework.repository.SinglePokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitPokemonDataSource): PokemonRemoteDataSource

    @Binds
    fun repositoryLocalDataSource(dataSource: RoomFavoritesDataSource): FavoriteLocalDataSource

    @Binds
    fun bindsPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository

    @Binds
    fun bindsSinglePokemonRepository(repository: SinglePokemonRepositoryImpl): SinglePokemonRepository

    @Binds
    fun bindFavoriteRepository(repository: FavoriteRepositoryImpl): FavoritesRepository
}