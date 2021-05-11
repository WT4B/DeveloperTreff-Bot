package de.wt4b.devtreffbot.manager;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;

public class EmoteManager {

    private static EmoteManager instance;

    public EmoteManager(){
        instance = this;
    }

    public Emote getEmoteByName(Guild guild, String name){
        return guild.getEmotes().stream().filter(emote -> emote.getName().contains(name)).findFirst().orElse(null);
    }

    public String getEmoteAsMention(Guild guild, String name){
        return guild.getEmotes().stream().filter(emote -> emote.getName().contains(name)).findFirst().get().getAsMention();
    }

    public static EmoteManager getInstance() {
        return instance;
    }
}
