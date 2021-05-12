package de.wt4b.devtreffbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import de.wt4b.devtreffbot.manager.EmoteManager;
import net.dv8tion.jda.api.entities.Emote;

@CommandInfo(name = "react", description = "Reagiere auf eine Nachricht")
public class ReactCommand extends Command {

    public ReactCommand(){
        this.name = "react";
        this.help = "Reagiere auf eine Nachricht";
        this.hidden = false;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().delete().queue();
        String[] args = event.getArgs().split(" ");
        if(args.length < 2) {
            event.replyInDm("Verwende: !react <MessageID> <Reaction...>");
            return;
        }
        EmoteManager emoteManager = EmoteManager.getInstance();
        for(int i = 1; i < args.length; i++){
            Emote emote = emoteManager.getEmoteByName(event.getGuild(), args[i].split(":")[1]);
            event.getTextChannel().addReactionById(args[0], emote).queue();
        }
    }
}
