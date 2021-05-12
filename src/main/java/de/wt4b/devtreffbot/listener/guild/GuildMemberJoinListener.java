package de.wt4b.devtreffbot.listener.guild;

import de.wt4b.devtreffbot.manager.ChannelManager;
import de.wt4b.devtreffbot.manager.EmoteManager;
import de.wt4b.devtreffbot.manager.RoleManager;
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
        TextChannel textChannel = ChannelManager.getInstance().getTextChannelByName(guild, "eingangshalle");
        if(textChannel == null) return;
        EmoteManager emoteManager = EmoteManager.getInstance();
        textChannel.sendMessage("[**+**] " + member.getAsMention() + " " + emoteManager.getEmoteAsMention(guild, "peepoHey")).queue();
        Role role = RoleManager.getInstance().getRoleByName(guild, "User");
        if(role == null) return;
        guild.addRoleToMember(member, role).queue();
    }
}
