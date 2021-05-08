package de.wt4b.devtreffbot.listener.guild;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildMemberJoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();
        TextChannel textChannel = guild.getTextChannelById(840682766282391572L);
        if(textChannel == null) return;
        textChannel.sendMessage(":green_circle: Hey " + member.getAsMention() + ", willkommen auf dem " + guild.getName() + " Discord.").queue();
        Role role = guild.getRoleById(840707990034186293L);
        if(role == null) return;
        guild.addRoleToMember(member, role).queue();
    }
}
