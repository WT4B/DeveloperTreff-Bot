package de.wt4b.devtreffbot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import de.wt4b.devtreffbot.commands.ShutdownCommand;
import de.wt4b.devtreffbot.listener.guild.GuildMemberJoinListener;
import de.wt4b.devtreffbot.listener.guild.GuildMemberRemoveListener;
import de.wt4b.devtreffbot.listener.message.MessageReactionAddListener;
import de.wt4b.devtreffbot.listener.message.MessageReactionRemoveListener;
import de.wt4b.devtreffbot.listener.message.MessageReceivedListener;
import de.wt4b.devtreffbot.manager.ChannelManager;
import de.wt4b.devtreffbot.manager.EmoteManager;
import de.wt4b.devtreffbot.manager.RoleManager;
import de.wt4b.devtreffbot.security.Security;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class DevTreff {

    private static DevTreff instance;
    private final JDABuilder builder;
    private final CommandClientBuilder commandClientBuilder;

    public static void main(String[] args) throws LoginException {
        new DevTreff();
    }

    public DevTreff() throws LoginException {
        instance = this;
        new ChannelManager();
        new EmoteManager();
        new RoleManager();

        this.builder = JDABuilder.createDefault(Security.TOKEN);
        this.builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS);
        this.builder.enableCache(CacheFlag.EMOTE);
        this.builder.setStatus(OnlineStatus.ONLINE);

        this.commandClientBuilder = new CommandClientBuilder();
        this.commandClientBuilder.setPrefix("!");
        this.commandClientBuilder.setOwnerId("321878167386325003");
        this.commandClientBuilder.setCoOwnerIds(Security.PRIVATEID);
        this.commandClientBuilder.setActivity(Activity.playing("programmieren"));

        registerCommands();
        registerListener();

        this.builder.build();
        System.out.println("Bot startet...");
    }

    private void registerListener(){
        this.builder.addEventListeners(new GuildMemberJoinListener());
        this.builder.addEventListeners(new GuildMemberRemoveListener());

        this.builder.addEventListeners(new MessageReactionAddListener());
        this.builder.addEventListeners(new MessageReactionRemoveListener());
        this.builder.addEventListeners(new MessageReceivedListener());
    }

    private void registerCommands(){
        this.commandClientBuilder.addCommand(new ShutdownCommand());

        this.builder.addEventListeners(this.commandClientBuilder.build());
    }

    public static DevTreff getInstance() {
        return instance;
    }

    public JDABuilder getBuilder() {
        return builder;
    }
}
