package com.example.pokedex.core.data.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
) : Parcelable
