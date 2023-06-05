package com.example.pokedex.presentation.detail.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.network.domain.Stats
import com.example.pokedex.databinding.ItemPokemonDetailBinding

class PokemonDetailViewHolder(
    itemPokemonDetailBinding: ItemPokemonDetailBinding
): RecyclerView.ViewHolder(itemPokemonDetailBinding.root) {

    private val statName = itemPokemonDetailBinding.statName
    private val statCount = itemPokemonDetailBinding.statCount

    fun bind(stat: Stats){

        statName.text = stat.stat.name.capitalize()

        if (stat.stat.name.contains("-")) {
            val first = stat.stat.name.substringBefore("-").capitalize()
            val second = stat.stat.name.substringAfter("-").capitalize()

            "$first - $second".also { statName.text = it }
        }

        statCount.text = stat.base_stat.toString()
    }

    companion object {
        fun create(parent: ViewGroup): PokemonDetailViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPokemonDetailBinding.inflate(inflater)
            return PokemonDetailViewHolder(itemBinding)
        }
    }
}