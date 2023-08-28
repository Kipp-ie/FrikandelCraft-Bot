package dev.kippenboutske.discordbot;

import dev.kippenboutske.discordbot.commands.*;
import dev.kippenboutske.discordbot.listeners.*;
import dev.kippenboutske.discordbot.managers.SlashCommandManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Main {



    private ShardManager shardManager;

    public Main() throws LoginException, InterruptedException {


        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("MTE0MjU3MTM1MTY2MDU3NjkxOQ.GfWG9u.yHMb0WuKHHp7XlSefxj98vPvOtNAiAuPHiadKM");
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setActivity(Activity.playing("NebulaMC Beta"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
        shardManager = builder.build();
        shardManager.addEventListener(
                // Manager
                new SlashCommandManager(),
                // Listeners
                new JoinListener(),
                new ApplyButtonListener(),
                new MessageReceivedListener(),
                new TicketButtonListener(),
                new ModalListener(),
                // Lavalink
                // Commands
                new TestCommand(),
                new UserAvatar(),
                new SadCat(),
                new Oogway(),
                new Help(),
                new SetTicketCommand(),
                new Embed(),
                new Apply()

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
