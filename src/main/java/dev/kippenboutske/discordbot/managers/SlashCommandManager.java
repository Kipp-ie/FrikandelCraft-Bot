package dev.kippenboutske.discordbot.managers;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class SlashCommandManager extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commands = new ArrayList<>();

        commands.add(Commands.slash("test", "test command")); // dit is echt pure nachtmerrie deze oplossing ik heb hier te lang over gedaan en te veel random variable voor aangemaakt fucking hell aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.



        event.getGuild().updateCommands().addCommands(commands).queue();



    }
}
