package de.wt4b.devtreffbot.listener.guild;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildMemberRemoveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        Guild guild = event.getGuild();
        TextChannel textChannel = guild.getTextChannelById(840682766282391572L);
        if(textChannel == null) return;
        textChannel.sendMessage(":red_circle: " + event.getUser().getAsMention() + " hat den Server verlassen.").queue();
        Member member = event.getMember();
        if(member == null) return;
        for(Role role : member.getRoles()) guild.removeRoleFromMember(member, role).queue();
    }
}
