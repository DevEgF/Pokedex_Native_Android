package com.example.pokedex.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Pokemon
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.utils.getPicUrl

class HomeViewHolder(
    itemPokemonBinding: ItemPokemonBinding
): RecyclerView.ViewHolder(itemPokemonBinding.root) {

    private val textName = itemPokemonBinding.pokemonTextName
    private val imagePokemon = itemPokemonBinding.pokemonImage
    var picture: String? = ""

    fun bind(pokemon: Pokemon) {
        picture = pokemon.url.getPicUrl()

        textName.text = pokemon.name.capitalize()
        Glide.with(itemView)
            .load(picture)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imagePokemon)
    }

    companion object{
        fun create(parent: ViewGroup): HomeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPokemonBinding.inflate(inflater, parent, false)
            return  HomeViewHolder(itemBinding)
        }
    }
}