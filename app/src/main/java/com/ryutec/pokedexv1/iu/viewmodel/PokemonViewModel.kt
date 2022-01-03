package com.ryutec.pokedexv1.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryutec.pokedexv1.data.model.detalles.PokemonSprites
import com.ryutec.pokedexv1.data.model.detalles.Types
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import com.ryutec.pokedexv1.dominio.GetDetallesUseCase
import com.ryutec.pokedexv1.dominio.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    //Injeccion de dependencias
    private val getoPokemonsUseCase: GetPokemonsUseCase,
    private val getoDetailsUseCase: GetDetallesUseCase
) : ViewModel() {

    val pokemonModel = MutableLiveData<List<PokemonModel>>()
    val pokemonTypes = MutableLiveData<List<Types>>()
    val pokemonSprites = MutableLiveData<PokemonSprites>()
    val pokemonName = MutableLiveData<String>()

    //llamada al caso de uso para almacenar en memoria todos los pokemon
    fun onCreate(nuevo:Int) {
        viewModelScope.launch {
            val result = getoPokemonsUseCase("pokemon?limit=20&offset=$nuevo")
            if (!result.isNullOrEmpty()){
                pokemonModel.postValue(result)
            }
        }
    }

    fun getDetails(numero:String){
        viewModelScope.launch {
            val result = getoDetailsUseCase("pokemon/$numero")
            if (!result.sprites.equals(null)){
                pokemonSprites.postValue(result.sprites)
            }
            if (!result.types.isNullOrEmpty()){
                pokemonTypes.postValue(result.types)
            }
            if (!result.name.equals("")){
                pokemonName.postValue(result.name)
            }
        }
    }
}