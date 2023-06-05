package com.example.pokedex.presentation.home.viewholder

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.data.network.domain.PokemonResult
import com.example.pokedex.utils.PokemonDetailsNavigate
import com.example.pokedex.utils.getPicUrl

class HomeViewHolder(
    itemPokemonBinding: ItemPokemonBinding,
    private val pokemonDetailsNavigate: PokemonDetailsNavigate
): RecyclerView.ViewHolder(itemPokemonBinding.root) {

    private val textName = itemPokemonBinding.pokemonTextName
    private val imagePokemon = itemPokemonBinding.pokemonImage
    var dominantColor: Int = 0
    var picture: String? = ""

    fun bind(pokemon: PokemonResult) {
        picture = pokemon.url.getPicUrl()

        textName.text = pokemon.name.capitalize()
        Glide.with(itemView)
            .load(picture)
            .fallback(R.drawable.ic_img_loading_error)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val drawable = resource as BitmapDrawable
                    val bitmap = drawable.bitmap
                    Palette.Builder(bitmap).generate{
                        it?.let{ palette ->
                            dominantColor = palette.getDominantColor(
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.white
                                )
                            )

                            imagePokemon.setBackgroundColor(dominantColor)
                        }
                    }
                    return false
                }
            })
            .into(imagePokemon)

        itemView.setOnClickListener {
            pokemonDetailsNavigate.invoke(pokemon, dominantColor, picture)
        }
    }

    companion object{
        fun create(
            parent: ViewGroup,
            pokemonDetailsNavigate: PokemonDetailsNavigate
        ): HomeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPokemonBinding.inflate(inflater, parent, false)
            return  HomeViewHolder(itemBinding, pokemonDetailsNavigate)
        }
    }
}