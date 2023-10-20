package dev.kippenboutske.discordbot.commands;

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
            event.reply("Warning System Activated").queue();
            OptionMapping option = event.getOption("user");
            if (!Files.exists(Path.of("Data"))) {
                System.out.print("Warning system experienced an error, please check the code!");
            } else if (Files.exists(Path.of("Data"))) {
                System.out.print("Data folder checked, proceeding.");
                if (!Files.exists(Path.of("Data/" + option.getAsUser().getId()))) {
                    System.out.print("User folder not found creating one....");
                    new File("Data/" + option.getAsUser().getId()).mkdirs();
                    if (!Files.exists(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"))) {
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
                        System.out.print("Warns.txt not found, creating one....");
                        File warntxt = new File("Data/" + option.getAsUser().getId() + "/warns.txt");
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
                        option.getAsMember().timeoutFor(10, TimeUnit.MINUTES);
                    } else if (Files.exists(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"))) {
                        System.out.print("Warns.txt found");
                        Scanner myReader = null;
                        Scanner myReader2 = null;
                        try {
                            myReader = new Scanner(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"));
                            myReader2 = new Scanner(Path.of("Data/" + option.getAsUser().getId() + "/warns.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {

                            if (myReader.nextLine().equals("1")) {
                                System.out.print("It's 1");
                                option.getAsMember().timeoutFor(15, TimeUnit.MINUTES);

                            } else if (myReader2.nextLine().equals("2")) {
                                System.out.print("It's a 2");
                                option.getAsMember().ban(14, TimeUnit.DAYS);
                            }


                        } finally {

                        }




                    }

                }
            }
        }
    }
}