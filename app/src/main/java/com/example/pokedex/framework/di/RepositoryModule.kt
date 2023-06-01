package com.example.pokedex.framework.di

import com.example.pokedex.data.network.remote.PokemonRemoteDataSource
import com.example.pokedex.data.network.remote.RetrofitPokemonDataSource
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.data.repository.PokemonRepositoryImpl
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
    fun bindsPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository
}