package dev.kippenboutske.discordbot;

import dev.kippenboutske.discordbot.commands.*;
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

public class Main {

    private Dotenv dotenv;
    private ShardManager shardManager;

    public Main() throws LoginException {
        dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");




        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("NebulaMC.xyz"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MODERATION);
        shardManager = builder.build();
        shardManager.addEventListener(
                // Manager
                new SlashCommandManager(),
                // Listeners
                new JoinListener(),
                new MessageReceivedListener(),
                // Commands
                new TestCommand(),
                new UserAvatar(),
                new SadCat(),
                new Oogway(),
                new Help()
            );

    }

    public static void main(String[] args) {
        try {
            Main bot = new Main();
        } catch (LoginException e) {
            System.out.println("Bot token invalid!");
        }
    }
}
