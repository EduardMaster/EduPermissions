package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsGroupRemovePermissionCommand :

    Command("removepermission", "removerpermissao") {

    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val group = manager.getGroup(nome)
        val perm = args[3]
        if (group != null) {
            group.removePermission(perm)
            sender.sendMessage(
                PermsMessages.message("group-remove-permission")
                    .replace("\$group", "" + nome)
                    .replace("\$permission", perm)
            )
            return
        }
        sender.sendMessage(
            PermsMessages.message("group-not-exists")
                .replace("\$group", "" + nome)
        )

    }


    init {
        usage = "/permissions group removepermission <group> <permission>"
        description = "Remover uma permissão do grupo"
    }
}