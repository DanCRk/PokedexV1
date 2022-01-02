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
    private lateinit var listaPokemon:MutableList<PokemonModel>
    var offset:Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //obtener la lista de pokemon
        pokemonViewModel.onCreate(offset)
        pokemonViewModel.pokemonModel.observe(this, {
            setUpRecyclerView(it)

            //refrescarRecyclerView()
        })


       // pokemonViewModel.details()
        pokemonViewModel.pokemonDetails.observe(this, {
        })

        //cargarMas()
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

    private fun setUpRecyclerView(list:List<PokemonModel>){
        adapter = RecyclerAdapter(list, this)
        binding.recycler.layoutManager = GridLayoutManager(this,2)
        binding.recycler.adapter = adapter
        if (!list.isNullOrEmpty()){

        }else{
            showError()
        }

    }

    private fun refrescarRecyclerView(){ if(!listaPokemon.isNullOrEmpty()){ adapter.notifyDataSetChanged() }else{ showError() } }

    private fun showError(){ Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show() }
}