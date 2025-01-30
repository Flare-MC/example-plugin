package com.flare.example

import com.flare.sdk.FlareConfiguration
import com.flare.sdk.annotations.DataFolder
import com.flare.sdk.annotations.PlatformAccessor
import com.flare.sdk.annotations.PluginConfiguration
import com.flare.sdk.event.EventBus
import com.flare.sdk.event.impl.player.PlayerJoinEvent
import com.flare.sdk.platform.Platform
import java.io.File

/*
 * Project: example-plugin
 * Created at: 29/9/24 16:42
 * Created by: Dani-error
 */
class ExamplePlugin {

    @PlatformAccessor
    private lateinit var platform: Platform

    @DataFolder
    private lateinit var dataFolder: File

    @PluginConfiguration
    private lateinit var configuration: FlareConfiguration

    fun onLoad() {
        println("Loading ${configuration.name} plugin...")
    }

    fun onEnable() {
        println("Hello, it works! (Running on ${platform.platformType.name} - ${dataFolder.absolutePath})")

        EventBus.register(PlayerJoinEvent::class.java) {
            println("${it.player.name} just joined the game!")
        }
    }

    fun onDisable() {
        println("Disabling plugin...")
    }

}