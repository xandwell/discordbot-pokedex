package snaive.pokedex.commands;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class RawPokemonInfo {

    private String title, flavorText, generation, type, sprite, officialArt;

    public RawPokemonInfo(Integer dexNumber) {
        getRawData(dexNumber);
    }

    public RawPokemonInfo(String pokemonName) {
        getRawData(getDexNumber(pokemonName));
    }

    private void getRawData(Integer dexNumber) {
        PokeApi pokeApi = new PokeApiClient();
        Pokemon pokemon = pokeApi.getPokemon(dexNumber);
        PokemonSpecies pokemonSpecies = pokeApi.getPokemonSpecies(dexNumber);


        title = "#" + dexNumber + " - " + StringUtils.capitalize(pokemonSpecies.getName());
        flavorText = getFlavorText(pokemonSpecies);
        generation = String.valueOf(pokemonSpecies.getGeneration().getId());
        type = getPokemonTypes(pokemon);
        sprite = pokemon.getSprites().getFrontDefault();
        assert sprite != null;
        officialArt = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
                + dexNumber + ".png";
    }


    private String getFlavorText (PokemonSpecies pkmn) {
        // search and filter only english flavor texts
        List<String> englishFlavorTexts = pkmn.getFlavorTextEntries().stream()
                .filter(entry -> "en".equals(entry.getLanguage().getName()))
                .map(PokemonSpeciesFlavorText::getFlavorText)
                .map(StringUtils::normalizeSpace)
                .toList();

        // returns the last index of available flavor texts
        return englishFlavorTexts.get(englishFlavorTexts.size() - 1);
    }

    private String getPokemonTypes(Pokemon pkmn) {
        List<String> typeList = pkmn.getTypes().stream()
                .map(PokemonType::getType)
                .map(NamedApiResource::getName)
                .map(StringUtils::capitalize)
                .toList();

        return String.join("/", typeList);
    }

    public Integer getDexNumber(String pokemonName) {
        PokeApi pokeApi = new PokeApiClient();

        return pokeApi.getPokemonSpeciesList(0, 1025).getResults().stream()
                .filter(entry -> pokemonName.equalsIgnoreCase(entry.getName()))
                .map(NamedApiResource::getId)
                .toList().get(0);
    }

    public String getTitle() {
        return title;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public String getGeneration() {
        return generation;
    }

    public String getType() {
        return type;
    }

    public String getSpriteURL() {
        return sprite;
    }

    public String getOfficialArtURL() {
        return officialArt;
    }
}
