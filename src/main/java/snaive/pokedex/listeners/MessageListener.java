package snaive.pokedex.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;
        Message message = event.getMessage();

        // getting bot id
        String idString = "<@" + event.getJDA().getSelfUser().getIdLong() + ">";

        // if bot mentioned, reply
        if (message.getContentRaw().startsWith(idString)) {
            String preparedMessage = "";



            event.getGuild().getTextChannelById(event.getChannel().getId()).sendMessage(preparedMessage).queue();
        }
    }
}
