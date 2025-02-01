package com.flare.example.command

import com.flare.sdk.command.Command
import com.flare.sdk.command.CommandContext
import com.flare.sdk.command.CommandInfo
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor


/*
 * Project: example-plugin
 * Created at: 1/2/25 19:27
 * Created by: Dani-error
 */
class ExampleCommand : Command(CommandInfo.create("example").inGameOnly(false).subCommands(ExampleFooCommand()).build()) {

    override fun execute(context: CommandContext) {
        context.sender.sendMessage(Component.text("Looks like the command works!").color(NamedTextColor.GOLD))
    }

    override fun suggest(context: CommandContext): List<String> {
        val args = context.args
        val lastArg = args[args.size - 1]

        return listOf("foo", "bar")
            .filter { it.regionMatches(0, lastArg, 0, lastArg.length, ignoreCase = true) }
            .toList()
    }

}

class ExampleFooCommand : Command("foo", false) {

    override fun execute(context: CommandContext) {
        context.sender.sendMessage(Component.text("Looks like the subcommand works!").color(NamedTextColor.YELLOW))
    }

    override fun suggest(context: CommandContext): List<String> {
        val args = context.args
        val lastArg = args[args.size - 1]

        return listOf("foo2", "bar2")
            .filter { it.regionMatches(0, lastArg, 0, lastArg.length, ignoreCase = true) }
            .toList()
    }

}