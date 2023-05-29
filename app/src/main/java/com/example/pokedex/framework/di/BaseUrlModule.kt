package com.example.pokedex.framework.di

import com.example.pokedex.BuildConfig
import com.example.pokedex.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = BuildConfig.BASE_URL
}