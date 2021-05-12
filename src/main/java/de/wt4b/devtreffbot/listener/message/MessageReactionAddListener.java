package de.wt4b.devtreffbot.listener.message;

import de.wt4b.devtreffbot.manager.RoleManager;
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
        if(textChannel.getName().contains("vorschläge")){
            if(!emote.contains("accepted") && !emote.contains("maybe") && !emote.contains("declined")) return;
            if(member.isOwner() || member.getUser().isBot()) return;
            event.getReaction().removeReaction(member.getUser()).queue();
        }else if(textChannel.getName().contains("rollen")){
            if(emote.contains("java")) addRole(guild, member, "Java");
            else if(emote.contains("kotlin")) addRole(guild, member, "Kotlin");
            else if(emote.contains("html")) addRole(guild, member, "Web");
            else if(emote.contains("csharp")) addRole(guild, member, "C#");
            else if(emote.contains("python")) addRole(guild, member, "Python");
            else if(emote.contains("minecraft")) addRole(guild, member, "Minecraft");
            else if(emote.contains("github")) addRole(guild, member, "GitHub");
        }
    }

    private void addRole(Guild guild, Member member, String roleName){
        Role role = RoleManager.getInstance().getRoleByName(guild, roleName);
        if(role == null) return;
        guild.addRoleToMember(member, role).queue();
    }
}
