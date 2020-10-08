package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI

class PermissionsSaveCommand : Command("save", "salvar") {
    override fun onCommand(sender: Sender, args: List<String>) {
        PermissionsAPI.plugin.save()
        sender.sendMessage("§aPlugin EduPermissions salvado com sucesso")
    }


    init {
        description = "Salvar as permissões e grupos"
    }
}