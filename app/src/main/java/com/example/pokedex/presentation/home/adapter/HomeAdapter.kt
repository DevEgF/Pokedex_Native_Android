package com.example.pokedex.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.presentation.home.viewholder.HomeViewHolder

class HomeAdapter: PagingDataAdapter<PokemonResult, HomeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       return HomeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object: DiffUtil.ItemCallback<PokemonResult>(){
            override fun areItemsTheSame(
                oldItem: PokemonResult,
                newItem: PokemonResult
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonResult,
                newItem: PokemonResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}