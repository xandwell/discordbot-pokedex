package snaive.pokedex.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import snaive.pokedex.commands.EmbedInfo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;

        TextChannel textChannel = event.getGuild().getTextChannelById(event.getChannel().getId());
        assert textChannel != null;

        String rawMessage = event.getMessage().getContentRaw();

        // getting bot id
        String idString = "<@" + event.getJDA().getSelfUser().getIdLong() + ">";

        // if bot mentioned, reply
        if (rawMessage.startsWith(idString)) {
            ArrayList<String> splittedMessage = split(rawMessage);

            // if bot is tagged without messages, will reply simple message
            if (splittedMessage.size() == 1) {
                sendMessageToChannel(textChannel, "Kenapa bang");
                return;
            }

            if (splittedMessage.get(1).equalsIgnoreCase("info")) {

                // if "info" command is not followed up (empty), it will return this
                if (splittedMessage.size() == 2) {
                    sendMessageToChannel(textChannel, "info apa bang?");
                    return;
                }

                EmbedInfo embedInfo = new EmbedInfo();
                EmbedBuilder result = embedInfo.embedInfoPokemon(splittedMessage.get(2));
                sendEmbedToChannel(textChannel, result);

            }
        }
    }

    public void sendMessageToChannel(TextChannel textChannel, String preparedMessage) {
        textChannel.sendMessage(preparedMessage).queue();
    }

    public void sendEmbedToChannel(TextChannel textChannel, EmbedBuilder embedBuilder) {
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    private ArrayList<String> split(String subjectString){
        ArrayList<String> matchList = new ArrayList<>();
        Pattern regex = Pattern.compile("\"([^\"\\\\]*(?:\\\\.[^\"\\\\]*)*)\"|'([^'\\\\]*(?:\\\\.[^'\\\\]*)*)'|[^\\s]+");
        Matcher regexMatcher = regex.matcher(subjectString);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }
        return matchList;
    }
}
