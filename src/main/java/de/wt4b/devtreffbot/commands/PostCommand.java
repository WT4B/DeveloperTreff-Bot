package de.wt4b.devtreffbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

@CommandInfo(name = "post", description = "Sende eine vorgefertigte Nachricht")
public class PostCommand extends Command {

    public PostCommand(){
        this.name = "post";
        this.help = "Sende eine vorgefertigte Nachricht";
        this.hidden = false;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().delete().queue();
        String[] args = event.getArgs().split(" ");
        if(args.length != 1){
            event.replyInDm("Verwende: !post <Regeln>");
            return;
        }
        if(args[0].equalsIgnoreCase("regeln")){
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Regeln des DeveloperTreff Discord Server")
                    .setDescription("\nDies sind die Regeln des DeveloperTreff Discord Server.\n**Als Mitglied des DeveloperTreff Discord Server hast Du diese zu befolgen! Ansonsten musst Du mit Konsequenzen rechnen. Unwissenheit schütz nicht vor einer Strafe!**")
                    .setColor(Color.RED)
                    .setFooter("Die Regeln können jederzeit überarbeitet werden! | Letztes Update » 13.05.2021", null)
                    .addField("§1 | Discord Terms of use", "Die [Discord Nutzungsbedingungen](https://discord.com/terms) und die [Discord Community-Richtlinien](https://discord.com/guidelines) müssen befolgt werden.", false)
                    .addField("§2 | Verhalten", "Sei zu allen Usern freundlich und respektvoll. Behandle sie so, wie du behandeln werden willst.", false)
                    .addField("§3 | Beleidigung", "Jegliche Art von Beleidigung ist untersagt.", false)
                    .addField("§4 | NSFW", "Jegliche Art von pornographischen, menschenverachtenden oder persönlichen Inhalten sind verboten.", false)
                    .addField("§5 | Diskriminierung", "Jegliche Art von Diskriminierung ist untersagt.", false)
                    .addField("§6 | Werbung", "Jegliche Art von Werbung ist untersagt, es sei denn, der Channel ist explizit dafür gemacht. DM-Werbung ist verboten.", false)
                    .addField("§7 | Spamming", "Das Spammen von Nachrichten, Emojis, u.ä. ist verboten.", false)
                    .addField("§8 | Pinging", "Das Pingen von Teammitgliedern und ganzen Rollen ist untersagt. Ghostpings sind verboten! Andere User mit Pings zu spammen ist ebenso nicht erlaubt.", false)
                    .addField("Regelverstöße an WT4B melden.", "", false);
            event.getTextChannel().sendMessage(embedBuilder.build()).queue();
        }
    }
}
