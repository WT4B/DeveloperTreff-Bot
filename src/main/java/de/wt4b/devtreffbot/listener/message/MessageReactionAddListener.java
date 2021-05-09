package de.wt4b.devtreffbot.listener.message;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReactionAddListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if(!(event.getChannel() instanceof TextChannel)) return;
        TextChannel textChannel = event.getTextChannel();
        if(event.getReactionEmote().isEmoji()) return;
        String emote = event.getReactionEmote().getEmote().getName();
        if(textChannel.getIdLong() == 840881135280979998L){
            if(!emote.contains("accepted") && !emote.contains("maybe") && !emote.contains("declined")) return;
            Member member = event.getMember();
            if(member == null) return;
            if(member.isOwner() || member.getUser().isBot()) return;
            event.getReaction().removeReaction(member.getUser()).queue();
        }
    }
}
