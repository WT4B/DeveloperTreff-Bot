package de.wt4b.devtreffbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Calendar;
import java.util.Locale;

@CommandInfo(name = "embed", description = "Erstelle eine Embed Nachricht")
public class EmbedCommand extends Command {

    public EmbedCommand(){
        this.name = "embed";
        this.help = "Erstelle eine Embed Nachricht";
        this.hidden = false;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().delete().queue();
        String[] args = event.getArgs().split(" ");
        if(args.length < 3){
            event.replyInDm("Verwende: !embed <Farbe> <Title> <Description> <Field(Name~value)>");
            event.replyInDm("Verwende: ';' für Leerzeichen");
            return;
        }
        String title = args[1];
        String description = args[2];
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(getColorByName(args[0]))
                .setTitle(title.equalsIgnoreCase("null") ? null : title.replaceAll(";", " "))
                .setDescription(description.equalsIgnoreCase("null") ? null : description.replaceAll(";", " "))
                .setFooter("DeveloperTreff - " + Calendar.getInstance(Locale.GERMANY).getTime(), event.getSelfUser().getAvatarUrl());
        for(int i = 3; i < args.length; i++) {
            String field = args[i];
            String name = field.split("~")[0];
            String value = args[i].split("~")[1];
            embedBuilder.addField(name.equalsIgnoreCase("null") ? "" : name.replaceAll(";", " "),
                    value.equalsIgnoreCase("null") ? "" : value.replaceAll(";", " "), false);
        }
        event.getTextChannel().sendMessage(embedBuilder.build()).queue();
    }

    private Color getColorByName(String name){
        switch(name.toLowerCase()){
            case "weiß": return Color.WHITE;
            case "hellgrau": return Color.LIGHT_GRAY;
            case "grau": return Color.GRAY;
            case "dunkelgrau": return Color.DARK_GRAY;
            case "schwarz": return Color.BLACK;
            case "rot": return Color.RED;
            case "pink": return Color.PINK;
            case "orange": return Color.ORANGE;
            case "gelb": return Color.YELLOW;
            case "grün": return Color.GREEN;
            case "magenta": return Color.MAGENTA;
            case "türkis": return Color.CYAN;
            case "blau": return Color.BLUE;
            default: return Color.CYAN;
        }
    }
}
