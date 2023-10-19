package dev.kippenboutske.discordbot.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ServerStatus extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("serverstatus")) {
            try {
                while (true) {
                    String sURL = "https://api.mcstatus.io/v2/status/java/play.nebulamc.xyz"; // URL for the API

                    // Connect to the URL
                    URL url = new URL(sURL);
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    // Convert the response to a JSON object
                    JsonParser jsonParser = new JsonParser();
                    JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) connection.getContent()));

                    // Extract the "players" field as a JsonObject
                    JsonObject rootObject = root.getAsJsonObject();
                    String online2 = rootObject.get("online").getAsString();
                    MessageHistory history = MessageHistory.getHistoryFromBeginning(event.getGuild().getTextChannelById("1164655353137483928")).complete();
                    List<Message> mess = history.getRetrievedHistory();
                    for(Message m: mess){
                        m.delete().queue();
                    }



                    EmbedBuilder embed = new EmbedBuilder();
                    if (online2.equals("true")) {
                        JsonObject playersObject = rootObject.getAsJsonObject("players");
                        embed.setColor(Color.GREEN);
                        int onlinePlayers = playersObject.get("online").getAsInt();
                        String players = String.valueOf(onlinePlayers);
                        event.getGuild().getTextChannelById("1164655353137483928").getManager().setName("\uD83D\uDFE2｜\uD835\uDDE6\uD835\uDE01\uD835\uDDEE\uD835\uDE01\uD835\uDE02\uD835\uDE00").queue();

                        embed.setTitle("LunarisMC Server Status");
                        embed.addField("Status", "Online", false );
                        embed.addField("Players Online", "There are currently " + players + " player(s) online", false );
                        embed.addField("How to join?", "Good news! The server is online, you can join with the ip (BLANK)!", false );

                    } else if (online2.equals("false")) {
                        embed.setColor(Color.RED);
                        event.getGuild().getTextChannelById("1164655353137483928").getManager().setName("\uD83D\uDD34｜\uD835\uDDE6\uD835\uDE01\uD835\uDDEE\uD835\uDE01\uD835\uDE02\uD835\uDE00").queue();
                         embed.setTitle("LunarisMC Server Status ");
                         embed.addField("Status", "Offline", false );
                         embed.addField("We are offline, what now?", "We are doing our best at starting the server as fast as possible again! In the meantime, you can check our website or interact with our community!", false );
                    }
                    embed.setFooter("LunarisMC <3");





                    event.getGuild().getTextChannelById("1164655353137483928").sendMessageEmbeds(embed.build()).queue();


                    Thread.sleep(5 * 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}