package de.wt4b.devtreffbot.listener.message;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        Message message = event.getMessage();
        if(!(event.getChannel() instanceof TextChannel)) return;
        TextChannel textChannel = event.getTextChannel();
        if(textChannel.getIdLong() == 840881135280979998L){
            message.addReaction("👍").queue();
            message.addReaction("👎").queue();
            message.addReaction(getEmoteByName(guild, "accepted")).queue();
            message.addReaction(getEmoteByName(guild, "maybe")).queue();
            message.addReaction(getEmoteByName(guild, "declined")).queue();
        }
    }

    private Emote getEmoteByName(Guild guild, String name){
        return guild.getEmotes().stream().filter(emote -> emote.getName().contains(name)).findFirst().orElse(null);
    }
}
