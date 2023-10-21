package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.Route;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Clear extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("clear")) {
            MessageHistory history = event.getChannel().getHistory();
            List<Message> messages = history.retrievePast(100);
        }

    }
}
