package de.wt4b.devtreffbot.listener.guild;

import de.wt4b.devtreffbot.manager.ChannelManager;
import de.wt4b.devtreffbot.manager.EmoteManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildMemberRemoveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        Guild guild = event.getGuild();
        TextChannel textChannel = ChannelManager.getInstance().getTextChannelByName(guild, "eingangshalle");
        if(textChannel == null) return;
        EmoteManager emoteManager = EmoteManager.getInstance();
        textChannel.sendMessage("[**-**] " + event.getUser().getAsMention() + " " + emoteManager.getEmoteAsMention(guild, "peepoBye")).queue();
        Member member = event.getMember();
        if(member == null) return;
        for(Role role : member.getRoles()) guild.removeRoleFromMember(member, role).queue();
    }
}
