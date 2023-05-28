package com.example.pokedex.core.data.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonResult(
    val name: String,
    val url: String
) : Parcelable