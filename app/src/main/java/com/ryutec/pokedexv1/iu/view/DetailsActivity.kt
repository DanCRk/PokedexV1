package com.ryutec.pokedexv1.iu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import com.ryutec.pokedexv1.databinding.ActivityDetailsBinding
import com.ryutec.pokedexv1.iu.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private val listaPokemon = mutableListOf<PokemonResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numero = intent.getStringExtra("numero").toString()

        pokemonViewModel.getDetails(numero)

        pokemonViewModel.pokemonTypes.observe(this,{
            binding.tipo.text = it[0].type.name
        })

        pokemonViewModel.pokemonName.observe(this, {
            binding.nombre.text = it
        })

    }
}