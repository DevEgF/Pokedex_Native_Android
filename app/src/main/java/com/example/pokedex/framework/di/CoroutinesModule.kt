package com.example.pokedex.framework.di

import com.example.pokedex.data.usecase.base.AppCoroutinesDispatchers
import com.example.pokedex.data.usecase.base.CoroutinesDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {
    @Binds
    fun bindsDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}