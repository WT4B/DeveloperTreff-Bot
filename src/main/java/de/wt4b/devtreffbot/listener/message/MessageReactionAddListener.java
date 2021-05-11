package de.wt4b.devtreffbot.listener.message;

import de.wt4b.devtreffbot.reaction.ReactionChannel;
import de.wt4b.devtreffbot.reaction.ReactionRole;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReactionAddListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        Guild guild = event.getGuild();
        if(!(event.getChannel() instanceof TextChannel)) return;
        TextChannel textChannel = event.getTextChannel();
        Member member = event.getMember();
        if(member == null) return;
        if(event.getReactionEmote().isEmoji()) return;
        String emote = event.getReactionEmote().getEmote().getName();
        if(textChannel.getIdLong() == ReactionChannel.SUGGESTIONS.getChannelID()){
            if(!emote.contains("accepted") && !emote.contains("maybe") && !emote.contains("declined")) return;
            if(member.isOwner() || member.getUser().isBot()) return;
            event.getReaction().removeReaction(member.getUser()).queue();
        }else if(textChannel.getIdLong() == ReactionChannel.ROLES.getChannelID()){
            if(emote.contains("java")) addRole(guild, member, ReactionRole.JAVA);
            else if(emote.contains("kotlin")) addRole(guild, member, ReactionRole.KOTLIN);
            else if(emote.contains("html")) addRole(guild, member, ReactionRole.WEB);
            else if(emote.contains("csharp")) addRole(guild, member, ReactionRole.CSHARP);
            else if(emote.contains("python")) addRole(guild, member, ReactionRole.PYTHON);
            else if(emote.contains("minecraft")) addRole(guild, member, ReactionRole.MINECRAFT);
            else if(emote.contains("github")) addRole(guild, member, ReactionRole.GITHUB);
        }
    }

    private void addRole(Guild guild, Member member, ReactionRole reactionRole){
        Role role = guild.getRoleById(reactionRole.getRoleID());
        if(role == null) return;
        guild.addRoleToMember(member, role).queue();
    }
}
