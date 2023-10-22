package dev.kippenboutske.discordbot.listeners;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class OnMessage extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getChannel().getId().equals("1165589363497914368")) {
            String message = event.getMessage().getContentDisplay();
            String message2 = message.replace(" ", "+");
            String sURL = "https://api.popcat.xyz/chatbot?msg=" + message2 + "&owner=Lunaris+Team&botname=Lunaris"; // URL for the API
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
            String online2 = null;
            if (rootObject.get("text") == null) {
                return;
            } else {
                String string = rootObject.get("text").getAsString();
                online2 = string;

            }

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(online2);

            event.getChannel().sendMessageEmbeds(embed.build()).queue();

        } else {
            return;
        }

}}
