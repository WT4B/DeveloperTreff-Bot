package de.wt4b.devtreffbot.listener.message;

import de.wt4b.devtreffbot.manager.EmoteManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();
        if(!(event.getChannel() instanceof TextChannel)) return;
        TextChannel textChannel = event.getTextChannel();
        Guild guild = event.getGuild();
        if(textChannel.getName().contains("vorschläge")){
            EmoteManager emoteManager = EmoteManager.getInstance();
            message.addReaction("👍").queue();
            message.addReaction("👎").queue();
            message.addReaction(emoteManager.getEmoteByName(guild, "accepted")).queue();
            message.addReaction(emoteManager.getEmoteByName(guild, "maybe")).queue();
            message.addReaction(emoteManager.getEmoteByName(guild, "declined")).queue();
        }
    }
}
