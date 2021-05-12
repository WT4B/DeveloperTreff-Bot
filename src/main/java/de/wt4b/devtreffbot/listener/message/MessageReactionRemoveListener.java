package de.wt4b.devtreffbot.listener.message;

import de.wt4b.devtreffbot.manager.RoleManager;
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
        if(textChannel.getName().contains("rollen")){
            if(emote.contains("java")) removeRole(guild, user, "Java");
            else if(emote.contains("kotlin")) removeRole(guild, user, "Kotlin");
            else if(emote.contains("html")) removeRole(guild, user, "Web");
            else if(emote.contains("csharp")) removeRole(guild, user, "C#");
            else if(emote.contains("python")) removeRole(guild, user, "Python");
            else if(emote.contains("minecraft")) removeRole(guild, user, "Minecraft");
            else if(emote.contains("github")) removeRole(guild, user, "GitHub");
        }
    }

    private void removeRole(Guild guild, User user, String roleName){
        Role role = RoleManager.getInstance().getRoleByName(guild, roleName);
        if(role == null) return;
        guild.removeRoleFromMember(user.getIdLong(), role).queue();
    }
}
