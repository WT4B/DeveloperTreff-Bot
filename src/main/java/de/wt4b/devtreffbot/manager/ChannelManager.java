package de.wt4b.devtreffbot.manager;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class ChannelManager {

    private static ChannelManager instance;

    public ChannelManager(){
        instance = this;
    }

    public TextChannel getTextChannelByName(Guild guild, String name){
        return guild.getTextChannels().stream().filter(textChannel -> textChannel.getName().contains(name)).findFirst().orElse(null);
    }

    public static ChannelManager getInstance() {
        return instance;
    }
}
