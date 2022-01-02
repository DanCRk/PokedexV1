package com.ryutec.pokedexv1.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryutec.pokedexv1.data.Repository
import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import com.ryutec.pokedexv1.dominio.GetPokemonsUseCase
import com.ryutec.pokedexv1.dominio.GetSpritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    //Injeccion de dependencias
    private val getoPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    val pokemonModel = MutableLiveData<List<PokemonModel>>()

    //llamada al caso de uso para almacenar en memoria todos los pokemon
    fun onCreate(nuevo:Int) {
        viewModelScope.launch {
            val result = getoPokemonsUseCase("pokemon?limit=20&offset=$nuevo")
            if (!result.isNullOrEmpty()){
                pokemonModel.postValue(result)
            }
        }
    }
}