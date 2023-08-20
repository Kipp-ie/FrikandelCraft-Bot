package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getChannel().equals(event.getGuild().getTextChannelById("1142579445773901925"))){
            if(!event.getMember().getUser().isBot())
                event.getMessage().delete().queue();

            }
        }

    }
