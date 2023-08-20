package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.awt.*;

public class UserAvatar extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("avatar")){

            OptionMapping option = event.getOption("user");
            String url = option.getAsUser().getAvatarUrl();
            String name = option.getAsUser().getName();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Here is " + name + "'s avatar!");
            embed.setImage((url));
            embed.setColor(Color.magenta);

            event.replyEmbeds(embed.build()).queue();


        }
    }
}
