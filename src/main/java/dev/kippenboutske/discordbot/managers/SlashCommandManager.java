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
        commands.add(Commands.slash("sadcat", "Make a sad cat meme!")
                .addOption(OptionType.STRING, "text", "Change the text of the Sad Cat meme"));
        commands.add(Commands.slash("oogway", "Create an Oogway quote!")
                .addOption(OptionType.STRING, "text", "Change the oogway quote!"));
        commands.add(Commands.slash("help", "Get a list of NebulaBot's commands!"));
        commands.add(Commands.slash("settickets", "Create a ticket embed"));
        commands.add(Commands.slash("embed", "Admin command to place embeds"));
        commands.add(Commands.slash("setapply", "Admin command to place embed apply's"));

        event.getGuild().updateCommands().addCommands(commands).queue();



    }
}
