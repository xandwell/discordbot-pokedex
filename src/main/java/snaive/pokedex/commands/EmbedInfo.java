package snaive.pokedex.commands;

import net.dv8tion.jda.api.EmbedBuilder;

public class EmbedInfo extends EmbedBuilder {
    public EmbedBuilder embedInfoPokemon(String pokemonName) {

        EmbedBuilder embed = new EmbedBuilder();

        RawPokemonInfo rawPokemonInfo = new RawPokemonInfo(pokemonName);
        embed.setTitle(rawPokemonInfo.getTitle());
        embed.setDescription(rawPokemonInfo.getFlavorText())
                .addField("Type", rawPokemonInfo.getType(), true)
                .addField("Generation", rawPokemonInfo.getGeneration(), true);
        embed.setImage(rawPokemonInfo.getOfficialArtURL());
        embed.setThumbnail(rawPokemonInfo.getSpriteURL());
        embed.setFooter("Made by Dex.");

        return embed;
    }
}
