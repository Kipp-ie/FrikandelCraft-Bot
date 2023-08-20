package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.awt.*;

public class SadCat extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("sadcat")){

            String option = event.getOption("text").getAsString();
            String popcat = "https://api.popcat.xyz/sadcat?text=";
            String option2 = option + " ";
            String option3 = option2.trim().replaceAll(" ", "+");
            String url = popcat + option3;

            EmbedBuilder embed = new EmbedBuilder();
            embed.setImage(url);
            embed.setTitle("Here is your sad cat meme!");
            embed.setColor(new Color(101, 47, 150));

            event.replyEmbeds(embed.build()).queue();

            return;

        }
    }
}
