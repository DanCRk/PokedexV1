package com.ryutec.pokedexv1.data

import com.ryutec.pokedexv1.data.model.pokemon.PokemonModel
import com.ryutec.pokedexv1.data.model.PokemonProvider
import com.ryutec.pokedexv1.data.model.detalles.PokemonDetails
import com.ryutec.pokedexv1.data.model.pokemon.PokemonResponse
import com.ryutec.pokedexv1.data.network.PokemonService
import javax.inject.Inject

class Repository @Inject constructor(private val api : PokemonService, private val pokemonProvider: PokemonProvider) {

    public var listAllPokemons:MutableList<PokemonModel> = emptyList<PokemonModel>().toMutableList()

    suspend fun getAllPokemons(url:String): PokemonResponse {
        //Pedimos la lista de pokemons
        val response = api.getPokemon(url)
        //La guardamos en el provider
        pokemonProvider.pokemons = response
        //Devolvemos la lista de pokemon
        return response
    }

    suspend fun getDetails(url:String): PokemonDetails {
        //Pedimos la lista de pokemons
        val response = api.getDettails(url)
        //La guardamos en el provider
        pokemonProvider.details= response
        //Devolvemos la lista de pokemon
        return response
    }
}