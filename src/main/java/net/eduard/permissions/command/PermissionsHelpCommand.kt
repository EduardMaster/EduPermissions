package net.eduard.permissions.command

import net.eduard.api.lib.manager.CommandManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class PermissionsHelpCommand : CommandManager("help", "ajuda", "?") {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        for (sub in parent!!.getCommands().values) {
            if (sender.hasPermission(sub.permission)) {
                sender.sendMessage("§3" + sub.usage + " §b" + sub.description)
            }
        }
        return true
    }

    init {
        description = "Ver como usa os comandos"
    }
}