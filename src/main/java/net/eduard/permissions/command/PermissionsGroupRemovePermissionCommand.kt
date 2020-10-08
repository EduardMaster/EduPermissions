package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.EduPermissions

class PermissionsGroupRemovePermissionCommand :

    Command("removepermission", "removerpermissao") {

    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = EduPermissions.instance.manager
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val group = manager.getGroup(nome)
        val perm = args[3]
        if (group != null) {
            group.permissions.remove(perm)
            sender.sendMessage(
                EduPermissions.instance.message("group-remove-permission")
                    .replace("\$group", "" + nome)
                    .replace("\$permission", perm)
            )
            return
        }
        sender.sendMessage(
            EduPermissions.instance.message("group-not-exists")
                .replace("\$group", "" + nome)
        )

    }


    init {
        usage = "/permissions group addpermission <group> <permission>"
        description = "Remover uma permissão do grupo"
    }
}