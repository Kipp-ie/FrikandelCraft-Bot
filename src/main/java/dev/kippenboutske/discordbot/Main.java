package dev.kippenboutske.discordbot;

import dev.kippenboutske.discordbot.commands.*;
import dev.kippenboutske.discordbot.listeners.JoinListener;
import dev.kippenboutske.discordbot.listeners.MessageReceivedListener;
import dev.kippenboutske.discordbot.listeners.ModalListener;
import dev.kippenboutske.discordbot.listeners.TicketButtonListener;
import dev.kippenboutske.discordbot.managers.SlashCommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Main {

    private ShardManager shardManager;

    public Main() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("MTE0MjU3MTM1MTY2MDU3NjkxOQ.GfWG9u.yHMb0WuKHHp7XlSefxj98vPvOtNAiAuPHiadKM");
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("NebulaMC.xyz"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
        shardManager = builder.build();
        shardManager.addEventListener(
                // Manager
                new SlashCommandManager(),
                // Listeners
                new JoinListener(),
                new MessageReceivedListener(),
                new TicketButtonListener(),
                new ModalListener(),
                // Commands
                new TestCommand(),
                new UserAvatar(),
                new SadCat(),
                new Oogway(),
                new Help(),
                new SetTicketCommand()

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
