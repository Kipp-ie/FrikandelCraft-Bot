package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {


        String memberName = event.getMember().getAsMention();
        String memberAvatarUrl = event.getMember().getAvatarUrl();
        String memberID = event.getMember().getId();

        event.getGuild().getTextChannelById("1142579445773901925").sendMessage(memberName + " is de server gejoined TEST").queue();




    }
}
