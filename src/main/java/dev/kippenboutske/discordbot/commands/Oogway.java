package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Oogway extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("oogway")){

            String option = event.getOption("text").getAsString();
            String popcat = "https://api.popcat.xyz/oogway?text=";
            String option2 = option + " ";
            String option3 = option2.trim().replaceAll(" ", "+");
            String url = popcat + option3;

            EmbedBuilder embed = new EmbedBuilder();
            embed.setImage(url);
            embed.setTitle("Here is your oogway quote!");

            event.replyEmbeds(embed.build()).queue();

        }
    }
}
