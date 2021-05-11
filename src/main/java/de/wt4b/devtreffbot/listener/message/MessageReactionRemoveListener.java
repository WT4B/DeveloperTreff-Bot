package de.wt4b.devtreffbot.listener.message;

import de.wt4b.devtreffbot.reaction.ReactionChannel;
import de.wt4b.devtreffbot.reaction.ReactionRole;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReactionRemoveListener extends ListenerAdapter {

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        Guild guild = event.getGuild();
        if(!(event.getChannel() instanceof TextChannel)) return;
        TextChannel textChannel = event.getTextChannel();
        User user = event.getUser();
        if(user == null) return;
        if(event.getReactionEmote().isEmoji()) return;
        String emote = event.getReactionEmote().getEmote().getName();
        if(textChannel.getIdLong() == ReactionChannel.ROLES.getChannelID()){
            if(emote.contains("java")) removeRole(guild, user, ReactionRole.JAVA);
            else if(emote.contains("kotlin")) removeRole(guild, user, ReactionRole.KOTLIN);
            else if(emote.contains("html")) removeRole(guild, user, ReactionRole.WEB);
            else if(emote.contains("csharp")) removeRole(guild, user, ReactionRole.CSHARP);
            else if(emote.contains("python")) removeRole(guild, user, ReactionRole.PYTHON);
            else if(emote.contains("minecraft")) removeRole(guild, user, ReactionRole.MINECRAFT);
            else if(emote.contains("github")) removeRole(guild, user, ReactionRole.GITHUB);
        }
    }

    private void removeRole(Guild guild, User user, ReactionRole reactionRole){
        Role role = guild.getRoleById(reactionRole.getRoleID());
        if(role == null) return;
        guild.removeRoleFromMember(user.getIdLong(), role).queue();
    }
}
