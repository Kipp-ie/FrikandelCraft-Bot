package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.EnumSet;

public class TicketButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("openTicket")) {
            event.reply("Where do you need help with?").setEphemeral(true)
                    .addActionRow(
                            StringSelectMenu.create("ticket-subject")
                                    .addOptions(SelectOption.of("Questions", "questions")
                                            .withEmoji(Emoji.fromUnicode("U+2753"))
                                            .withDescription("Ask a question about NebulaMC!"))
                                    .addOptions(SelectOption.of("Bugs", "Bugs")
                                            .withEmoji(Emoji.fromUnicode("U+1F41B"))
                                            .withDescription("Did you encounter a bug that you want to report?"))
                                    .addOption("Purchases", "purchases", "Ask or report about purchases")// SelectOption with only the label, value, and description
                                    .build())
                    .queue();

        } else if (event.getButton().getId().equals("closeButton")) {
            if (event.getMember().getRoles().equals(event.getGuild().getRoleById("1144661426208776282"))) {
                event.getInteraction().getChannel().delete().queue();
            } else {
                TextInput subject = TextInput.create("name", "What's your username?", TextInputStyle.SHORT)
                        .setPlaceholder("Example: @kippenboutske")
                        .setMinLength(1)
                        .setMaxLength(40) // or setRequiredRange(10, 100)
                        .build();

                TextInput staff = TextInput.create("staff", "Who helped you today??", TextInputStyle.SHORT)
                        .setPlaceholder("This can be a name or a @, please leave blank if you don't know anymore!")
                        .setMinLength(0)
                        .setMaxLength(50) // or setRequiredRange(10, 100)
                        .build();

                TextInput number = TextInput.create("number", "1 - 10 How do you rate your service?", TextInputStyle.SHORT)
                        .setPlaceholder("Pick a number 1 - 10")
                        .setMinLength(1)
                        .setMaxLength(50) // or setRequiredRange(10, 100)
                        .build();

                Modal modal = Modal.create("fmodal", "Feedback")
                        .addComponents(ActionRow.of(subject), ActionRow.of(staff), ActionRow.of(number))
                        .build();

                event.replyModal(modal).queue();

                return;
            }
        }
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("ticket-subject")) {
            Guild guild = event.getGuild();
            Member member = event.getMember();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.GREEN);
            embed.setTitle("Ticket");
            embed.setDescription("A staff member will be with you shortly!");
            embed.addField("Name", event.getMember().getEffectiveName(), false);
            embed.addField("Subject", event.getValues().get(0), false);
            embed.addField("Time", event.getTimeCreated().toString(), false);
            embed.setColor(new Color(101, 47, 150));


            guild.createTextChannel("Ticket-" + member.getEffectiveName().toLowerCase() + "-" + event.getValues().get(0), guild.getCategoryById("1143195932075229224"))
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getRoleById("1123716404655423659"), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();


            event.reply("Your ticket has been created!").setEphemeral(true).queue();
        }

    }

    private Button closeButton() {
        return net.dv8tion.jda.api.interactions.components.buttons.Button.danger("closeButton", "Close");
    }


}