package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MessageCheckSystem extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getMessage().getAuthor().isBot()) {
            return;
        } else if (!event.getMessage().getAuthor().isBot()) {
            Boolean kkk = event.getMessage().toString().toLowerCase().contains("kkk");
            Boolean kanker = event.getMessage().toString().toLowerCase().contains("kanker");
            Boolean tyfus = event.getMessage().toString().toLowerCase().contains("tyfus");
            Boolean kkr = event.getMessage().toString().toLowerCase().contains("kkr");
            Boolean kanker3 = event.getMessage().toString().toLowerCase().contains("k@nker");
            Boolean kank3r = event.getMessage().toString().toLowerCase().contains("kank3r");
            Boolean fuck = event.getMessage().toString().toLowerCase().contains("fuck");
            Boolean fucking = event.getMessage().toString().toLowerCase().contains("fucking");
            Boolean fuck2 = event.getMessage().toString().toLowerCase().contains("f*ck");
            Boolean kanker2 = event.getMessage().toString().toLowerCase().contains("k*nk*r");

            if (kkk || kanker || tyfus || kkr || kank3r || kanker3 || fuck || fucking || fuck2 || kanker2) {
                if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                    event.getGuild().timeoutFor(UserSnowflake.fromId(event.getAuthor().getId()), 8, TimeUnit.MINUTES).queue();
                    event.getMessage().delete().queue();
                    EmbedBuilder dmuser = new EmbedBuilder();
                    dmuser.setTitle("Flagged");
                    dmuser.setDescription("You have been flagged by our moderation system");
                    dmuser.addField("Reason", "You have been flagged for saying: " + event.getMessage().getContentDisplay().toString(), false);
                    dmuser.addField("Punishment", "Your message has been deleted and you now have a timeout of 8 minutes", false);
                    dmuser.addField("Feedback", "Do you believe this was a mistake? Then please wait out your timeout and make a ticket, we would be happy to assist you!", false);
                    dmuser.setColor(new Color(101, 47, 150));
                    event.getMessage().getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(dmuser.build())).queue();
                } else {
                    event.getMessage().addReaction(Emoji.fromUnicode("❌")).queue();
                }
            } else {
                if (Files.exists(Path.of("Data/" + event.getMember().getId()))) {
                    if (Files.exists(Path.of("Data/" + event.getMember().getId() + "/xp.txt"))) {
                        System.out.print("Xp file found");
                        Scanner myReader = null;
                        try {
                            myReader = new Scanner(Path.of("Data/" + event.getMember().getId() + "/xp.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String test = myReader.nextLine();
                        System.out.print(test);
                        int xptotal = Integer.sum(Integer.parseInt(test), 1);
                        System.out.print(" " + xptotal);
                        FileWriter filewriter = null;
                        try {
                            filewriter = new FileWriter("Data/" + event.getMember().getId() + "/xp.txt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            filewriter.write(String.valueOf(xptotal));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            filewriter.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scanner myReader2 = null;
                        try {
                            myReader2 = new Scanner(Path.of("Data/" + event.getMember().getId() + "/xp.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String xpfile = myReader2.nextLine();
                        System.out.print(xpfile);
                        if (xpfile.contains("10")) {
                            EmbedBuilder embed = new EmbedBuilder();
                            embed.setTitle("GG " + event.getMember().getEffectiveName() + "!");
                            embed.setThumbnail(event.getMember().getUser().getAvatarUrl());
                            embed.setDescription("You have reached Level 1! You received the Level 1 role!");
                            event.getChannel().sendMessageEmbeds(embed.build()).queue();
                            FileWriter filewriterlevel = null;
                            try {
                                filewriterlevel = new FileWriter(String.valueOf(Path.of("Data/" + event.getMember().getId() + "/level.txt")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.write("1");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.flush();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (xpfile.contains("25")) {
                            EmbedBuilder embed = new EmbedBuilder();
                            embed.setTitle("GG " + event.getMember().getEffectiveName() + "!");
                            embed.setThumbnail(event.getMember().getUser().getAvatarUrl());
                            embed.setDescription("You have reached Level 2!");
                            event.getChannel().sendMessageEmbeds(embed.build()).queue();
                            FileWriter filewriterlevel = null;
                            try {
                                filewriterlevel = new FileWriter(String.valueOf(Path.of("Data/" + event.getMember().getId() + "/level.txt")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.write("2");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.flush();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (xpfile.contains("45")) {
                            EmbedBuilder embed = new EmbedBuilder();
                            embed.setTitle("GG " + event.getMember().getEffectiveName() + "!");
                            embed.setThumbnail(event.getMember().getUser().getAvatarUrl());
                            embed.setDescription("You have reached Level 3!");
                            event.getChannel().sendMessageEmbeds(embed.build()).queue();
                            FileWriter filewriterlevel = null;
                            try {
                                filewriterlevel = new FileWriter(String.valueOf(Path.of("Data/" + event.getMember().getId() + "/level.txt")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.write("3");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.flush();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                filewriterlevel.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }


                    } else if (!Files.exists(Path.of("Data/" + event.getMember().getId() + "/xp.txt"))) {
                        try {
                            new File("Data/" + event.getMember().getUser().getId() + "/xp.txt").createNewFile();
                            new File("Data/" + event.getMember().getUser().getId() + "/level.txt").createNewFile();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        FileWriter myWriter = null;
                        FileWriter myWriter2 = null;
                        try {
                            myWriter = new FileWriter(String.valueOf(Path.of("Data/" + event.getMember().getId() + "/xp.txt")));
                            myWriter2 = new FileWriter(String.valueOf(Path.of("Data/" + event.getMember().getId() + "/level.txt")));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            myWriter.write("1");
                            myWriter2.write("0");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            myWriter.flush();
                            myWriter.close();
                            myWriter2.flush();
                            myWriter2.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                }
                        System.out.print("File created");
                    } else {
                    new File("Data/" + event.getMember().getId()).mkdir();
                    try {
                        new File("Data/" + event.getMember().getId() + "/xp.txt").createNewFile();
                        FileWriter myWriter = null;
                        try {
                            myWriter = new FileWriter(String.valueOf(Path.of("Data/" + event.getMember().getId() + "/xp.txt")));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            myWriter.write("1");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            myWriter.flush();
                            myWriter.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }}

