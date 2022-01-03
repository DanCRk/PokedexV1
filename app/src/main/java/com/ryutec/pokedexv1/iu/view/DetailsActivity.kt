package com.ryutec.pokedexv1.iu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.ryutec.pokedexv1.data.adapter.viewpager.SliderAdapter
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import com.ryutec.pokedexv1.databinding.ActivityDetailsBinding
import com.ryutec.pokedexv1.iu.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private val listaPokemon = mutableListOf<PokemonResponse>()
    private val sliderItems = mutableListOf<String>()
    private lateinit var adapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numero = intent.getStringExtra("numero").toString()

        pokemonViewModel.getDetails(numero)

        setUpViewPager()

        pokemonViewModel.pokemonSprites.observe(this, {
            if (!it.equals(null)){
                sliderItems.add(it.front_default)
                sliderItems.add(it.back_default)
                sliderItems.add(it.front_shiny)
                sliderItems.add(it.back_shiny)
                refrescar()
            }
        })

        pokemonViewModel.pokemonTypes.observe(this,{
            binding.tipo.text = it[0].type.name
        })

        pokemonViewModel.pokemonName.observe(this, {
            binding.nombre.text = it
        })

    }

    private fun setUpViewPager(){
        adapter = SliderAdapter(sliderItems, binding.sliderSprites)
        binding.sliderSprites.adapter = adapter
    }

    private fun refrescar(){ if(!sliderItems.isNullOrEmpty()){ adapter.notifyDataSetChanged() }else{ showError() } }

    private fun showError(){ Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show() }

}