package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ModalListener extends ListenerAdapter {
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().equals("fmodal")) {
            String name = event.getValue("name").getAsString();
            String staff = event.getValue("staff").getAsString();
            String number = event.getValue("number").getAsString();

            EmbedBuilder feedbackembed = new EmbedBuilder()
                    .setTitle("Feedback embed");

            event.getGuild().getTextChannelById("1143235851149652080").sendMessageEmbeds(feedbackembed.build()).queue();

            event.reply("Thanks for your request!").setEphemeral(true).queue();

            event.getInteraction().getChannel().delete().queue();

            EmbedBuilder dmuser = new EmbedBuilder();
            dmuser.setTitle("Ticket Closed");
            dmuser.setDescription("Your ticket has been closed, you can make a new ticket when you need support again!");
            dmuser.addField("Support", "We hope we could help you with your issue! Remember to have fun!", false);
            dmuser.setColor(new Color(101, 47, 150));


            event.getUser().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(dmuser.build())).queue();



            return;
        }
    }
}
