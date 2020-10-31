package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender

class PermissionsUserHelpCommand : Command("help", "ajuda", "?") {

    override fun onCommand(sender: Sender, args: List<String>) {
        for (sub in parent!!.subCommands) {
            if (sender.hasPermission(sub.permission)) {
                sender.sendMessage("§3" + sub.usage + " §b" + sub.description)
            }
        }
    }

    init {
        usage = "/permissions user help"
        description = "Ver como usa os comandos"
    }
}