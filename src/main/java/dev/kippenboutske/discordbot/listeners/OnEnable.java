package dev.kippenboutske.discordbot.listeners;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class OnEnable extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        if (!Files.exists(Path.of("Data"))) {
        new File("Data").mkdirs();
        System.out.print("Folder Created");
    } else {
            System.out.print("Found folder");
            return;
        }
}}
