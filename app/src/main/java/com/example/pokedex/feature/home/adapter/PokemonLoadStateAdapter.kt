package com.example.pokedex.feature.home.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.pokedex.feature.home.viewholder.PokemonLoadMoreStateViewHolder

class PokemonLoadStateAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<PokemonLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PokemonLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: PokemonLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}