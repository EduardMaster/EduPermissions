package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI

class PermissionsReloadCommand : Command("reload", "recarregar") {
    override fun onCommand(sender: Sender, args: List<String>) {
        PermissionsAPI.plugin.reload()
        sender.sendMessage("§aPlugin EduPermissions salvado com sucesso")
    }

    init {
        description = "Recarregar as permissões e grupos"
    }
}