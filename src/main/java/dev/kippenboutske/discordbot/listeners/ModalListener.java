package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
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

            //Creating a embed to be sent to the feedback channel
            EmbedBuilder feedbackembed = new EmbedBuilder();
                    feedbackembed.setTitle("Feedback embed");
                    feedbackembed.addField("Name", name, false);
                    feedbackembed.addField("Staff", staff, false);
                    feedbackembed.addField("Rating", number, false);
                    feedbackembed.setColor(new Color(101, 47, 150));


            //Getting the feedback channel by ID
            event.getJDA().getTextChannelById("1145314645289209987").sendMessageEmbeds(feedbackembed.build()).queue();

            //Tbh this doesn't do anything
            event.reply("Thanks for your request!").setEphemeral(true).queue();

            //Deleting the ticket
            event.getInteraction().getChannel().delete().queue();

            //Dm's the user after closing ticket
            EmbedBuilder dmuser = new EmbedBuilder();
            dmuser.setTitle("Ticket Closed");
            dmuser.setDescription("Your ticket has been closed, you can make a new ticket when you need support again!");
            dmuser.addField("Support", "We hope we could help you with your issue! Remember to have fun!", false);
            dmuser.addField("Feedback", "Your feedback has been sent to our staff team, we appreciate it!", false);
            dmuser.setColor(new Color(101, 47, 150));

            event.getUser().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(dmuser.build())).queue();



            return;
        }
        if (event.getModalId().equals("mediamodal")) {
            String name = event.getValue("name").getAsString();
            String staff = event.getValue("staff").getAsString();
            String number = event.getValue("number").getAsString();

            //Creating a embed to be sent to the feedback channel
            EmbedBuilder feedbackembed = new EmbedBuilder();
            feedbackembed.setTitle("Feedback embed");
            feedbackembed.addField("Name", name, false);
            feedbackembed.addField("Staff", staff, false);
            feedbackembed.addField("Rating", number, false);
            feedbackembed.setColor(new Color(101, 47, 150));


            //Getting the feedback channel by ID
            event.getJDA().getTextChannelById("1145314645289209987").sendMessageEmbeds(feedbackembed.build()).queue();

            //Tbh this doesn't do anything
            event.reply("Thanks for your request!").setEphemeral(true).queue();

            //Deleting the ticket
            event.getInteraction().getChannel().delete().queue();

            //Dm's the user after closing ticket
            EmbedBuilder dmuser = new EmbedBuilder();
            dmuser.setTitle("Ticket Closed");
            dmuser.setDescription("Your ticket has been closed, you can make a new ticket when you need support again!");
            dmuser.addField("Support", "We hope we could help you with your issue! Remember to have fun!", false);
            dmuser.addField("Feedback", "Your feedback has been sent to our staff team, we appreciate it!", false);
            dmuser.setColor(new Color(101, 47, 150));

            event.getUser().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(dmuser.build())).queue();



            return;
        }
    }
}
