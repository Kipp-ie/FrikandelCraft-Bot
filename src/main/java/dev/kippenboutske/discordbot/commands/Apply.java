package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

public class Apply extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("setapply")) {
            event.reply("Admin - Apply select menu")
                    .addActionRow(
                            StringSelectMenu.create("apply-select")
                                    .addOption("Media", "mediaapply", "Apply for a media role!")
                                    .addOption("Staff", "staffapply", "Apply for staff!")// SelectOption with only the label, value, and description
                                    .build()).queue();
        }
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (event.getMember().isOwner()) {
        if(event.getValues().get(0).equals("mediaapply")) {
            EmbedBuilder mediaembed = new EmbedBuilder();
            mediaembed.setTitle("TEST");
            event.replyEmbeds(mediaembed.build())
                    .setActionRow(
                            Button.success("mediaRole", "Apply for a media role")
                                    .withEmoji(Emoji.fromUnicode("U+1F3A5"))).queue();
        } else {

            event.reply("You don't have the permissions for this!").queue();
        }


        }
    }
}
