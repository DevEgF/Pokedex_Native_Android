package com.example.core.data.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stat(
    val name: String,
    val url: String
) : Parcelable