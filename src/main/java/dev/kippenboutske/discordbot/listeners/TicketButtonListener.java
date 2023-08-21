package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.EnumSet;

public class TicketButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("openTicket")){
            Guild guild = event.getGuild();
            Member member = event.getMember();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.GREEN);
            embed.setTitle("Ticket of" + member.getEffectiveName());
            embed.setDescription("A staff member will be with you shortly!");

            guild.createTextChannel("Ticket-" + member.getEffectiveName().toLowerCase(), guild.getCategoryById("1143195932075229224"))
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getRoleById("1123716404655423659"), EnumSet.of(Permission.VIEW_CHANNEL,Permission.MESSAGE_HISTORY), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();

            event.reply("Your ticket has been created!").setEphemeral(true).queue();
        } else if(event.getButton().getId().equals("closeButton")){
            event.getInteraction().getChannel().delete().queue();

        }
    }

    private Button closeButton(){
        return net.dv8tion.jda.api.interactions.components.buttons.Button.danger("closeButton", "Close");
    }
}
