package com.example.pokedex.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.framework.db.dao.FavoriteDao
import com.example.pokedex.framework.db.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}