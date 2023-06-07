package com.example.pokedex.presentation.detail

import androidx.annotation.StringRes

data class DetailChildVE(
    val name: String,
    val url: String
)

data class DetailParentVE(
    @StringRes
    val categoryStringResId: Int,
    val detailChildList: List<DetailChildVE> = listOf()
)
