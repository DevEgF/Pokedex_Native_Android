package com.example.pokedex.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.framework.db.utils.DbConstants

@Entity (tableName = DbConstants.FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(DbConstants.FAVORITES_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(DbConstants.FAVORITES_COLUMN_INFO_IMAGE_URL)
    val url: String
)

fun List<FavoriteEntity>.toPokemonResultModel() = map{
    PokemonResult(it.name,it.url)
}
