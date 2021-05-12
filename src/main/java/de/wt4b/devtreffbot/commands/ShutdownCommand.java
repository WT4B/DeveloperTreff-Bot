package de.wt4b.devtreffbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;

@CommandInfo(name = "shutdown", description = "Stoppt den Bot")
public class ShutdownCommand extends Command {

    public ShutdownCommand(){
        this.name = "shutdown";
        this.help = "Stoppt den Bot";
        this.hidden = true;
        this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getJDA().shutdown();
    }
}
