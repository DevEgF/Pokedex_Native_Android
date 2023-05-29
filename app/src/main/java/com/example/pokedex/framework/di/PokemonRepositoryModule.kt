package com.example.pokedex.framework.di

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PokemonRepositoryModule {

    @Binds
    fun bindsPokemonRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository
}