package com.example.pokedex.core.data.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int
) : Parcelable