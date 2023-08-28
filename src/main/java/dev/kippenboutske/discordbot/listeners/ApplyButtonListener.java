package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class ApplyButtonListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if(event.getButton().getId().equals("mediaRole")) {
            TextInput subject = TextInput.create("name", "What's your username?", TextInputStyle.SHORT)
                    .setPlaceholder("Example: @kippenboutske")
                    .setMinLength(1)
                    .setMaxLength(40) // or setRequiredRange(10, 100)
                    .build();

            TextInput staff = TextInput.create("staff", "Can you link your socials?", TextInputStyle.SHORT)
                    .setPlaceholder("Example: twitch.tv/kippenboutske")
                    .setMinLength(0)
                    .setMaxLength(50) // or setRequiredRange(10, 100)
                    .build();

            TextInput number = TextInput.create("number", "1 - 10 How do you rate your service?", TextInputStyle.SHORT)
                    .setPlaceholder("Pick a number 1 - 10")
                    .setMinLength(1)
                    .setMaxLength(50) // or setRequiredRange(10, 100)
                    .build();

            Modal mediaRoleModal = Modal.create("mediamodal", "Media Role Apply")
                    .addComponents(ActionRow.of(subject), ActionRow.of(staff), ActionRow.of(number))
                    .build();

            event.replyModal(mediaRoleModal).queue();

            return;


        }
    }
}
