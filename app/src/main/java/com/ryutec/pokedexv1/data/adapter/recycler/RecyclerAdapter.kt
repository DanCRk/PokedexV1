package com.ryutec.pokedexv1.data.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryutec.pokedexv1.R
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel

class RecyclerAdapter(val PokesList:List<PokemonModel>, val contexto: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val algo = PokesList[position]
        holder.bind(algo, contexto)
    }

    override fun getItemCount(): Int = PokesList.size

}