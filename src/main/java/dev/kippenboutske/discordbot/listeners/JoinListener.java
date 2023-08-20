package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class JoinListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        String usercount = String.valueOf(event.getGuild().getMemberCount());
        String memberName = event.getMember().getEffectiveName();
        String memberAvatarUrl = event.getUser().getAvatarUrl();
        String memberID = event.getMember().getId();

        event.getGuild().addRoleToMember(UserSnowflake.fromId(event.getMember().getId()), Objects.requireNonNull(event.getGuild().getRoleById("1119637633715408957")));

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Welcome in the NebulaMC Discord " + memberName + "!");
        embed.setColor(new Color(101, 47, 150));
        embed.setThumbnail(memberAvatarUrl);
        embed.addField("Welcome", "Hello! Please read the rules first before chatting! Have Fun!", false);
        embed.addField("Users", "There are now " + usercount + " members in this server!", false);



        event.getGuild().getTextChannelById("1142916465503248516").sendMessageEmbeds(embed.build()).queue();
        String members = String.valueOf(event.getGuild().getMemberCount());
        event.getGuild().getVoiceChannelById("1142916042889384146").getManager().setName("Total Users:" + members).queue();

    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        String members = String.valueOf(event.getGuild().getMemberCount());
        event.getGuild().getVoiceChannelById("1142916042889384146").getManager().setName("Total Users:" + members).queue();
    }
}
