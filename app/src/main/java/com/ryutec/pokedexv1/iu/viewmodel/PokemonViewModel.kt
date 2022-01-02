package com.ryutec.pokedexv1.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryutec.pokedexv1.data.Repository
import com.ryutec.pokedexv1.data.model.detalles.Types
import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import com.ryutec.pokedexv1.dominio.GetPokemonsUseCase
import com.ryutec.pokedexv1.dominio.GetTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    //Injeccion de dependencias
    private val getoPokemonsUseCase: GetPokemonsUseCase,
    private val getTypesUseCase: GetTypesUseCase,
    private var repository : Repository
) : ViewModel() {

    val pokemonModel = MutableLiveData<List<PokemonModel>>()
    val pokemonDetails = MutableLiveData<List<Types>>()

    //llamada al caso de uso para almacenar en memoria todos los pokemon
    fun onCreate(nuevo:Int) {
        viewModelScope.launch {
            val result = getoPokemonsUseCase("?limit=20&offset=$nuevo")
            val details = getTypesUseCase("/1/")
            if (!result.isNullOrEmpty()){
                repository.listAllPokemons.addAll(result)
                pokemonModel.postValue(result)
            }
            if (!details.isNullOrEmpty()){
                pokemonDetails.postValue(details)
            }
        }
    }

}