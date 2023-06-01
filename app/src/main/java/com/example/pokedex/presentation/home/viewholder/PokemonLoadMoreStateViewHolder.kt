package com.example.pokedex.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemPokemonLoadMoreStateBinding

class PokemonLoadMoreStateViewHolder(
    itemBinding: ItemPokemonLoadMoreStateBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemPokemonLoadMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadingMore
    private val textTryAgainMessage = binding.tryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState){
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PokemonLoadMoreStateViewHolder {
            val itemBinding = ItemPokemonLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return PokemonLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}