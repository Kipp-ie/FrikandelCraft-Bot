package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class Help extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("help")){
            event.reply("Where do you need help with?")
                    .addActionRow(
                            StringSelectMenu.create("help-select")
                                    .addOption("Images", "images", "Fun image tools!")
                                    .addOption("Support", "support", "Contact our support")// SelectOption with only the label, value, and description
                                    .build())
                    .queue();


            }
        }
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (event.getValues().get(0).equals("images")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Help - Images");
            embed.addField("Images", "/oogway - Make your own Oogway quote!", false );

            event.replyEmbeds(embed.build()).queue();

        }

        if (event.getValues().get(0).equals("support")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Help - Support");
            embed.addField("Support", "/support", false );

            event.replyEmbeds(embed.build()).queue();

        }

    }}


