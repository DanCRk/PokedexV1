package com.ryutec.pokedexv1.data.network

import com.ryutec.pokedexv1.data.model.detalles.PokemonDetails
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val api:PokemonAPIClient) {

    suspend fun getPokemon(url:String): PokemonResponse {
        return withContext(Dispatchers.IO){
            val response = api.getAllPokemon(url)
            response.body()!!
        }
    }

    suspend fun getDettails(url:String): PokemonDetails {
        return withContext(Dispatchers.IO){
            val response = api.getDetails(url)
            response.body()!!
        }
    }


}