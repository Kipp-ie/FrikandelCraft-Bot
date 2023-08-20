package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class PetPet extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("petpet")){

            String option = event.getOption("user").getAsUser().getAvatarUrl();
            String popcat = "https://api.popcat.xyz/pet?image=";
            String url = popcat + option;

            EmbedBuilder embed = new EmbedBuilder();
            embed.setImage(url);
            embed.setTitle("PETPET PETPET");
            embed.setColor(new Color(101, 47, 150));

            event.replyEmbeds(embed.build()).queue();

            return;
        }
    }
}
