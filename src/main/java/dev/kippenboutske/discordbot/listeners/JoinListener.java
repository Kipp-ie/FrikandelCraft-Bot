package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class JoinListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        String memberName = event.getMember().getEffectiveName();
        String memberAvatarUrl = event.getUser().getAvatarUrl();
        String memberID = event.getMember().getId();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Welcome in the NebulaMC Discord " + memberName + "!");
        embed.setColor(new Color(101, 47, 150));
        embed.setThumbnail(memberAvatarUrl);
        embed.addField("Welcome", "Hello! Please read the rules first before chatting! Have Fun!", false);

        event.getGuild().getTextChannelById("1142579445773901925").sendMessageEmbeds(embed.build()).queue();




    }
}
