package dev.kippenboutske.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Embed extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("embed")) {
            event.reply("Admin - Embed selection menu")
                    .addActionRow(
                            StringSelectMenu.create("embed-select")
                                    .addOption("Rules", "rules", "Rules")
                                    .addOption("Info", "info", "Information embed")// SelectOption with only the label, value, and description
                                    .build())
                    .queue();

        }
    }
    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if(event.getValues().get(0).equals("rules")){
            String rules = "1. Not spamming messages in: Dm's, channel's or in Voice channels, this also includes emoji spamming.\n" +
                    "2. Our server consists of multiple languages, but please only use English in the Discord server.\n" +
                    "3. Racism, NSFW and hate speech is not allowed in any channels or dm's.\n" +
                    "4. Respect members and staff members.\n" +
                    "5. No advertising is channels or dm's.\n" +
                    "6. Just because someone breaks the rules doesn't that mean that you can too\n" +
                    "7. DDOS Threats and dox threats are strictly prohibited.\n" +
                    "8. Do not blackmail other users or staff members.\n" +
                    "9. Please follow the Discord TOS (Terms of Services).";
        EmbedBuilder ruleembed = new EmbedBuilder();
        ruleembed.setTitle("NebulaMC - Rules");
        ruleembed.addField("Rules", rules, false);
        ruleembed.addField("Consequenses", "When breaking any of the rules staff will choose a suitable punishment.", false);
        ruleembed.setColor(new Color(101, 47, 150));

        event.getGuild().getTextChannelById("1143162459415388201").sendMessageEmbeds(ruleembed.build()).queue();
    }
        if(event.getValues().get(0).equals("info"));
}}


