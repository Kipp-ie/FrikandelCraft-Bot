package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MessageCheckSystem extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Boolean kkk = event.getMessage().toString().toLowerCase().contains("kkk");
        Boolean kanker = event.getMessage().toString().toLowerCase().contains("kanker");
        Boolean tyfus = event.getMessage().toString().toLowerCase().contains("tyfus");
        Boolean kkr = event.getMessage().toString().toLowerCase().contains("kkr");
        Boolean kanker3 = event.getMessage().toString().toLowerCase().contains("k@nker");
        Boolean kank3r = event.getMessage().toString().toLowerCase().contains("kank3r");
        Boolean fuck = event.getMessage().toString().toLowerCase().contains("fuck");
        Boolean fucking = event.getMessage().toString().toLowerCase().contains("fucking");
        Boolean fuck2 = event.getMessage().toString().toLowerCase().contains("f*ck");
        Boolean kanker2 = event.getMessage().toString().toLowerCase().contains("k*nk*r");

        if (kkk || kanker || tyfus || kkr || kank3r || kanker3 || fuck || fucking || fuck2 || kanker2) {
            if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.getGuild().timeoutFor(UserSnowflake.fromId(event.getAuthor().getId()), 8, TimeUnit.MINUTES).queue();
                event.getMessage().delete();
                EmbedBuilder dmuser = new EmbedBuilder();
                dmuser.setTitle("Flagged");
                dmuser.setDescription("You have been flagged by our moderation system");
                dmuser.addField("Reason", "You have been flagged for saying: " + event.getMessage().getContentDisplay().toString(), false);
                dmuser.addField("Punishment", "Your message has been deleted and you now have a timeout of 8 minutes", false);
                dmuser.addField("Feedback", "Do you believe this was a mistake? Then please wait out your timeout and make a ticket, we would be happy to assist you!", false);
                dmuser.setColor(new Color(101, 47, 150));
                event.getMessage().getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessageEmbeds(dmuser.build())).queue();
            } else {
                event.getMessage().addReaction(Emoji.fromUnicode("❌")).queue();
            }
        } else {
                return;
            }
    }
}
