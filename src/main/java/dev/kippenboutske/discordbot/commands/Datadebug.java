package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Datadebug extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("datadebug")) {
            if (Files.exists(Path.of("Data"))) {
                if (Files.exists(Path.of("Data/" + event.getUser().getId() ))) {
                    if (Files.exists(Path.of("Data/" + event.getUser().getId() + "/warns.txt"))) {

                    } else {
                        new File("Data/" + event.getUser().getId() + "/warns.txt");
                        event.reply("WARN File Created, Ending Debug").queue();
                    }
                } else {
                    new File("Data/" + event.getUser().getId()).mkdirs();
                    event.reply("Data Folder Created, please execute this command again").queue();
                }

            }
        }
    }
}
