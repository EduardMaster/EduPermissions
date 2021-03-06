package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI

class PermissionsGroupListCommand :
    Command("list", "lista") {


    override fun onCommand(sender: Sender, args: List<String>) {

        sender.sendMessage("§aCargos existens: §f" +
                PermissionsAPI.instance.groups.keys)


    }


    init {
        usage = "/permissions group list"
        description = "Ver como usa os comandos"
    }
}