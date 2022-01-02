package com.ryutec.pokedexv1.iu.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import com.ryutec.pokedexv1.databinding.ActivityMainBinding
import com.ryutec.pokedexv1.iu.viewmodel.PokemonViewModel
import com.ryutec.prueba.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val pokemonViewModel:PokemonViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter
    private var listaPokemon = emptyList<PokemonModel>()
    var offset:Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //obtener la lista de pokemon
        pokemonViewModel.onCreate(offset)
        cargarMas()
        pokemonViewModel.pokemonModel.observe(this, {
            listaPokemon.addAll(it)
            refrescarRecyclerView()
        })

        pokemonViewModel.pokemonDetails.observe(this, Observer {
            binding.textView.text = it[0].type[0].name
        })
        setUpRecyclerView()
    }

    private fun cargarMas(){
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                    offset +=20
                    pokemonViewModel.onCreate(offset)
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        adapter = RecyclerAdapter(listaPokemon, this)
        binding.recycler.layoutManager = GridLayoutManager(this,2)
        binding.recycler.adapter = adapter
    }

    private fun refrescarRecyclerView(){ if(!listaPokemon.isNullOrEmpty()){ adapter.notifyDataSetChanged() }else{ showError() } }

    private fun showError(){ Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show() }
}