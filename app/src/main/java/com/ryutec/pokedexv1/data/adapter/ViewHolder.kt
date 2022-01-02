package com.ryutec.prueba

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryutec.pokedexv1.SplashActivity
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import com.ryutec.pokedexv1.databinding.ItemBinding

class ViewHolder (val view: View):RecyclerView.ViewHolder(view){

    val binding = ItemBinding.bind(view)


    fun bind(Pkmn: PokemonModel, context:Context){
        val urlPartes :List<String> = Pkmn.url.split("/")
        val numero: String = urlPartes[6]
        binding.nombrePokemon.text = Pkmn.name
        if (numero<= 9.toString()){
            binding.numeroPokemon.text = "N.°00$numero"
        }else  if(numero<=99.toString()){
            binding.numeroPokemon.text = "N.°0$numero"
        }else {
            binding.numeroPokemon.text = "N.°$numero"
        }
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numero.png").into(binding.imgPokemon)
        view.setOnClickListener{
            var intent = Intent(context, SplashActivity::class.java)
            intent.putExtra("detalles", Pkmn.url)
            //context.startActivity(intent)
        }
    }
}