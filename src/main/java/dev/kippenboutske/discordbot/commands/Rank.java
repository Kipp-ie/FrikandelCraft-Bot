package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Rank extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("rank")) {
            event.getOption("user").getAsUser();
            if (Files.exists(Path.of("Data/" + event.getOption("user").getAsUser().getId() + "/xp.txt"))) {
                Scanner myReader3 = null;
                try {
                    myReader3 = new Scanner(Path.of("Data/" + event.getOption("user").getAsUser().getId() + "/xp.txt"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String xp = myReader3.nextLine();

                Scanner myReader2 = null;
                try {
                    myReader2 = new Scanner(Path.of("Data/" + event.getOption("user").getAsUser().getId() + "/level.txt"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String level = myReader2.nextLine();
                if (level.equals("0")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(event.getOption("user").getAsUser().getEffectiveName() + "is level 0");
                    embed.setDescription(xp + "/10 until level 1!");
                    event.replyEmbeds(embed.build()).queue();
                } else if (level.equals("1")) {
                    int xptotal = Math.subtractExact(Integer.parseInt(xp), 10);
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(event.getOption("user").getAsUser().getEffectiveName() + " is level 1");
                    embed.setDescription(xptotal + "/15 until level 2!");
                    event.replyEmbeds(embed.build()).queue();
                    
                } else if (level.equals("2")) {
                    int xptotal = Math.subtractExact(Integer.parseInt(xp), 25);
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(event.getOption("user").getAsUser().getEffectiveName() + " is level 2");
                    embed.setDescription(xptotal + "/20XP until level 3!");
                    event.replyEmbeds(embed.build()).queue();
                    
                }

            } else {
                event.reply("This user hasn't received any XP yet!").queue();
            }

        }
    }
}
