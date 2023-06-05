package com.example.pokedex.presentation.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.network.domain.Stats
import com.example.pokedex.presentation.detail.viewholder.PokemonDetailViewHolder

class PokemonDetailAdapter: RecyclerView.Adapter<PokemonDetailViewHolder>() {
    private val stats = ArrayList<Stats>()

    fun setStats(newList: ArrayList<Stats>) {
        stats.clear()
        stats.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonDetailViewHolder {
        return PokemonDetailViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return stats.size
    }

    override fun onBindViewHolder(holder: PokemonDetailViewHolder, position: Int) {
        holder.bind(stats[position])
    }
}