package com.example.pokedex.framework.di

import com.example.pokedex.domain.usecase.AddFavoriteUseCase
import com.example.pokedex.domain.usecase.AddFavoriteUseCaseImpl
import com.example.pokedex.domain.usecase.GetPokemonUseCase
import com.example.pokedex.domain.usecase.GetPokemonUseCaseImpl
import com.example.pokedex.domain.usecase.GetSinglePokemonUseCase
import com.example.pokedex.domain.usecase.GetSinglePokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import net.bytebuddy.implementation.bind.annotation.BindingPriority

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsGetPokemonUseCase(useCase: GetPokemonUseCaseImpl): GetPokemonUseCase

    @Binds
    fun bindsSinglePokemonUseCase(useCase: GetSinglePokemonUseCaseImpl): GetSinglePokemonUseCase

    @Binds
    fun addFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase
}