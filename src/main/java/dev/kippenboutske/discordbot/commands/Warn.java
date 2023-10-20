package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Warn extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("warn")) {
            OptionMapping option = event.getOption("user");
            if (!Files.exists(Path.of("Data"))) {
                System.out.print("Warning system experienced an error, please check the code!");
            } else if (Files.exists(Path.of("Data"))) {
                System.out.print("Data folder checked, proceeding.");
                if (!Files.exists(Path.of("Data/" + option.getAsUser().getId()))) {
                    System.out.print("User folder not found creating one....");
                    new File("Data/" + option.getAsUser().getId()).mkdirs();
                    if (!Files.exists(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"))) {
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle("Warn");
                        embed.setDescription(option.getAsUser().getAsMention() + "has been warned he has received 10 minutes of timeout");
                        embed.addField("Warnings", "You now have 1 warn!", true);
                        System.out.print("Warns.txt not found, creating one....");
                        event.replyEmbeds(embed.build()).queue();
                        String id = option.getAsUser().getId();
                        event.getGuild().timeoutFor(UserSnowflake.fromId(option.getAsMember().getId()), 10, TimeUnit.MINUTES).queue();
                        File warntxt = new File("Data/" + option.getAsUser().getId() + "/warns.txt");
                        System.out.print("Warns.txt not found, creating one....");
                        new File("Data/" + option.getAsUser().getId() + "/warns.txt");
                        FileWriter myWriter = null;
                        try {
                            myWriter = new FileWriter("Data/" + option.getAsUser().getId() + "/warns.txt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            myWriter.write("1");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            myWriter.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }

                } else if (Files.exists(Path.of("Data/" + option.getAsUser().getId()))) {
                    System.out.print("User data folder found.");
                    if (!Files.exists(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"))) {
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle("Warn");
                        embed.setDescription(option.getAsUser().getAsMention() + "has been warned he has received 10 minutes of timeout");
                        embed.addField("Warnings", "You now have 1 warn!", true);
                        event.getGuild().timeoutFor(UserSnowflake.fromId(option.getAsMember().getId()), 10, TimeUnit.MINUTES).queue();
                        System.out.print("Warns.txt not found, creating one....");
                        event.replyEmbeds(embed.build()).queue();
                        File warntxt = new File("Data/" + option.getAsUser().getId() + "/warns.txt");

                    } else if (Files.exists(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"))) {
                        System.out.print("Warns.txt found");
                        Scanner myReader = null;
                        Scanner myReader2 = null;
                        Scanner myReader3 = null;
                        Scanner myReader4 = null;
                        try {
                            myReader = new Scanner(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"));
                            myReader2 = new Scanner(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"));
                            myReader3 = new Scanner(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"));
                            myReader4 = new Scanner(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {

                            if (myReader.nextLine().equals("1")) {
                                FileWriter myWriter = null;
                                try {
                                    myWriter = new FileWriter("Data/" + option.getAsUser().getId() + "/warns.txt");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                try {
                                    myWriter.flush();
                                    myWriter.write("1");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    myWriter.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.print("It's 1");

                                EmbedBuilder embed = new EmbedBuilder();
                                embed.setTitle("Warn");
                                embed.setDescription(option.getAsUser().getAsMention() + " has been warned he has received 15 minutes of timeout");
                                embed.addField("Warnings", "You now have 2 warnings!", true);
                                event.getGuild().timeoutFor(UserSnowflake.fromId(option.getAsMember().getId()), 15, TimeUnit.MINUTES).queue();

                                event.replyEmbeds(embed.build()).queue();

                            } else if (myReader2.nextLine().equals("2")) {
                                System.out.print("It's a 2");
                                event.getGuild().ban(UserSnowflake.fromId(option.getAsMember().getId()), 7, TimeUnit.MINUTES).queue();
                                Files.delete(Path.of("Data/" + option.getAsUser().getId() + "/warn.txt"));
                                EmbedBuilder embed = new EmbedBuilder();
                                embed.setTitle("Warn");
                                embed.setDescription(option.getAsUser().getAsMention() + " has been banned for 7 days");
                                embed.addField("Warnings", "THe warns have been reset to zero, rejoin after 7 days to start fresh!", true);

                                event.replyEmbeds(embed.build()).queue();

                            }


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {

                        }




                    }

                }
            }
        }
    }
}