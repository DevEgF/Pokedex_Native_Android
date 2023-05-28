package com.example.core.data.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Suppress("ConstructorParameterNaming")
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
) : Parcelable