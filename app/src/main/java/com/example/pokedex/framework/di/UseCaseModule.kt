package com.example.pokedex.framework.di

import com.example.pokedex.data.usecase.GetPokemonUseCase
import com.example.pokedex.data.usecase.GetPokemonUseCaseImpl
import com.example.pokedex.data.usecase.GetSinglePokemonUseCase
import com.example.pokedex.data.usecase.GetSinglePokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsGetPokemonUseCase(useCase: GetPokemonUseCaseImpl): GetPokemonUseCase

    @Binds
    fun bindsSinglePokemonUseCase(useCase: GetSinglePokemonUseCaseImpl): GetSinglePokemonUseCase
}