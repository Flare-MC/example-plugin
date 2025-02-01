package com.flare.example

import com.flare.example.command.ExampleCommand
import com.flare.sdk.FlareConfiguration
import com.flare.sdk.annotations.CommandManagerAccessor
import com.flare.sdk.annotations.DataFolderAccessor
import com.flare.sdk.annotations.PlatformAccessor
import com.flare.sdk.annotations.PluginConfigurationAccessor
import com.flare.sdk.command.AbstractCommandManager
import com.flare.sdk.event.EventBus
import com.flare.sdk.event.impl.player.PlayerJoinEvent
import com.flare.sdk.platform.Platform
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import java.io.File

/*
 * Project: example-plugin
 * Created at: 29/9/24 16:42
 * Created by: Dani-error
 */
class ExamplePlugin {

    @PlatformAccessor
    private lateinit var platform: Platform

    @DataFolderAccessor
    private lateinit var dataFolder: File

    @PluginConfigurationAccessor
    private lateinit var configuration: FlareConfiguration

    @CommandManagerAccessor
    private lateinit var commandManager: AbstractCommandManager<*, *>

    fun onLoad() {
        commandManager.consoleSender.sendMessage(Component.text("Loading ${configuration.name} plugin...").color(NamedTextColor.GOLD))
    }

    fun onEnable() {
        commandManager.consoleSender.sendMessage(Component.text("Hello, it works! ").color(NamedTextColor.GOLD)
            .append(Component.text("(Running on ${platform.platformType.name} - ${dataFolder.absolutePath})").color(NamedTextColor.YELLOW)))

        commandManager.registerCommand(ExampleCommand())

        EventBus.register(PlayerJoinEvent::class.java) {
            commandManager.consoleSender.sendMessage(Component.text("${it.player.name} just joined the game!").color(NamedTextColor.YELLOW))
        }
    }

    fun onDisable() {
        commandManager.consoleSender.sendMessage(Component.text("Disabling plugin...").color(NamedTextColor.GOLD))
    }

}