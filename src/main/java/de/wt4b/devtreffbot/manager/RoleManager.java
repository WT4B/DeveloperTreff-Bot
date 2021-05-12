package de.wt4b.devtreffbot.manager;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

public class RoleManager {

    private static RoleManager instance;

    public RoleManager(){
        instance = this;
    }

    public Role getRoleByName(Guild guild, String name){
        return guild.getRoles().stream().filter(role -> role.getName().contains(name)).findFirst().orElse(null);
    }

    public static RoleManager getInstance() {
        return instance;
    }
}
