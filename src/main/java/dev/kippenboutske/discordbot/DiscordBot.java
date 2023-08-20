package dev.kippenboutske.discordbot;

import dev.kippenboutske.discordbot.listeners.JoinListener;
import dev.kippenboutske.discordbot.listeners.MessageReceivedListener;
import dev.kippenboutske.discordbot.managers.SlashCommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private Dotenv dotenv;
    private ShardManager shardManager;

    public DiscordBot() throws LoginException {
        dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("NebulaMC.xyz"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
        shardManager = builder.build();
        shardManager.addEventListener(
                // Manager
                new SlashCommandManager(),
                // Listeners
                new JoinListener(),
                new MessageReceivedListener()
            );

    }

    public static void main(String[] args) {
        try {
            DiscordBot bot = new DiscordBot();
        } catch (LoginException e) {
            System.out.println("Bot token invalid!");
        }
    }
}
