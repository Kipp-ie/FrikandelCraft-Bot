package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class SadCat extends ListenerAdapter {
     if(event.getName().equals("sadcat")){
        OptionMapping option = event.getOption("text");
        String popcat = https://api.popcat.xyz/sadcat?text=
        String text = option.replace(/ /g, '+');

        EmbedBuilder embed = new EmbedBuilder():
        embed.setImage(popcat + text);
        embed.setTitle("Here is your sad cat meme!");

        event.replyEmbeds(embed.build()).queue();

    }






}
