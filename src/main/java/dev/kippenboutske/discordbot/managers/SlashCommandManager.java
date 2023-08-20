package dev.kippenboutske.discordbot.managers;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import java.util.ArrayList;
import java.util.List;



public class SlashCommandManager extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commands = new ArrayList<>();

        commands.add(Commands.slash("test", "test command"));
        commands.add(Commands.slash( "avatar", "Grab the avatar by pinging a user!")
                        .addOption(OptionType.USER, "user", "Mention a user you want to grab the avatar from", true));


        event.getGuild().updateCommands().addCommands(commands).queue();



    }
}
