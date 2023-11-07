package dev.kippenboutske.discordbot;

import dev.kippenboutske.discordbot.commands.*;
import dev.kippenboutske.discordbot.listeners.*;
import dev.kippenboutske.discordbot.managers.SlashCommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Main {



    private ShardManager shardManager;

    public Main() throws LoginException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.watching("Happy Spooktober!"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();
        shardManager.addEventListener(
                // Manager
                new SlashCommandManager(),
                // Listeners
                new JoinListener(),
                new ApplyButtonListener(),
                new TicketButtonListener(),
                new ModalListener(),
                new OnEnable(),
                new MessageCheckSystem(),
                // Commands
                new UserAvatar(),
                new SadCat(),
                new Oogway(),
                new Help(),
                new SetTicketCommand(),
                new Embed(),
                new Apply(),
                new ServerStatus(),
                new Warn(),
                new Datadebug(),
                new Rank()
            );


    }

    public static void main(String[] args) {
        try {
            Main bot = new Main();
        } catch (LoginException e) {
            System.out.println("Bot token invalid!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
