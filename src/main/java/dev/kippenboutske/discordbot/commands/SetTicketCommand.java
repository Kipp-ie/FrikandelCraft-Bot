package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.awt.*;

public class SetTicketCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("settickets")){
            if(event.getMember().isOwner()){
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Create a ticket");
                embed.setDescription("Click on the button below to open a support ticket.");
                embed.setColor(new Color(101, 47, 150));
                event.getChannel().sendMessageEmbeds(embed.build())
                        .setActionRow(
                                Button.success("openTicket","Open a Ticket")
                                        .withEmoji(Emoji.fromUnicode("U+1F3AB"))).queue();

            } else {
                event.reply("Sorry, you don't have permission for this!").setEphemeral(true).queue();

            }
        }
    }
}
