package com.example.pokedex.framework.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.framework.db.AppDataBase
import com.example.pokedex.framework.db.utils.DbConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesAppDataBase(
        @ApplicationContext context:Context
    ) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        DbConstants.APP_DATABASE_NAME
    ).build()

    @Provides
    fun provideFavoriteDao(appDataBase: AppDataBase) = appDataBase.favoriteDao()
}