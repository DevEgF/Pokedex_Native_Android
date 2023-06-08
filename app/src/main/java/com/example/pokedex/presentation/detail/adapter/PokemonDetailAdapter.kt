package com.example.pokedex.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.network.domain.Stats
import com.example.pokedex.databinding.ItemPokemonDetailBinding

class PokemonDetailAdapter: RecyclerView.Adapter<PokemonDetailAdapter.PokemonDetailViewHolder>() {
    private val stats = ArrayList<Stats>()

    fun setStats(newList: ArrayList<Stats>) {
        stats.clear()
        stats.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonDetailViewHolder {
        return PokemonDetailViewHolder.create(parent)
    }

    override fun getItemCount() = stats.size

    override fun onBindViewHolder(holder: PokemonDetailViewHolder, position: Int) {
        holder.bind(stats[position])
    }

    class PokemonDetailViewHolder(
        itemBinding: ItemPokemonDetailBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val statName = itemBinding.statName
        private val statCount = itemBinding.statCount

        fun bind(stat: Stats) {

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
                val itemBinding = ItemPokemonDetailBinding
                    .inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return PokemonDetailViewHolder(itemBinding)
            }
        }
    }
}