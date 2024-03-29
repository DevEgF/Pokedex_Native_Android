package com.example.pokedex.feature.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pokedex.domain.model.PokemonResult
import com.example.pokedex.feature.home.viewholder.HomeViewHolder
import com.example.pokedex.utils.alias.PokemonDetailsNavigate

class HomeAdapter(
    private val pokemonDetailNavigate: PokemonDetailsNavigate
)
    : PagingDataAdapter<PokemonResult, HomeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       return HomeViewHolder.create(parent, pokemonDetailNavigate)
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