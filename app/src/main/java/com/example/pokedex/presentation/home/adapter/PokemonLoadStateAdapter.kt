package com.example.pokedex.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.pokedex.presentation.home.viewholder.PokemonLoadMoreStateViewHolder

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