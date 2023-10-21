package dev.kippenboutske.discordbot.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.ScheduledEvent;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerStatus extends ListenerAdapter {
    private ScheduledExecutorService scheduler;
    private SlashCommandInteractionEvent scheduledEvent;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("serverstatus")) {
            // Store the event for later use
            scheduledEvent = event;

            // Create a ScheduledExecutorService with a single thread
            scheduler = Executors.newSingleThreadScheduledExecutor();

            // Schedule a task to run every 10 seconds
            scheduler.scheduleAtFixedRate(() -> updateServerStatus(event), 0, 60, TimeUnit.SECONDS);
        }
    }

    private void updateServerStatus(SlashCommandInteractionEvent event) {
        if (scheduledEvent == null) {
            // Handle the case where scheduledEvent is not set (e.g., if the command wasn't invoked)
            return;
        }

        try {
            String sURL = "https://api.mcstatus.io/v2/status/java/play.nebulamc.xyz"; // URL for the API
            URL url = null;
            try {
                url = new URL(sURL);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            URLConnection connection = null;
            try {
                connection = url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.connect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Convert the response to a JSON object
            JsonParser jsonParser = new JsonParser();
            JsonElement root = null;
            try {
                root = jsonParser.parse(new InputStreamReader((InputStream) connection.getContent()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Extract the "players" field as a JsonObject
            JsonObject rootObject = root.getAsJsonObject();
            String online2 = rootObject.get("online").getAsString();

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





            event.getGuild().getTextChannelById("1164655353137483928").editMessageEmbedsById("1164825821475770421", embed.build()).queue();


        } finally {

        }
    }
}
