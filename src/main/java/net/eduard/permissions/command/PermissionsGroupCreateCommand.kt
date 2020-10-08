package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.EduPermissions

class PermissionsGroupCreateCommand :
    Command("create", "criar") {

    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = EduPermissions.instance.manager
        if (args.size < 3) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        var group = manager.getGroup(nome)
        if (group == null) {
            group = manager.createGroup(nome, "", "")
            sender.sendMessage(
                EduPermissions.instance.message("group-created")
                    .replace("\$group", "" + nome)
            )
        } else {
            sender.sendMessage(
                EduPermissions.instance.message("group-already-exists")
                    .replace("\$group", "" + nome)
            )
        }

    }


    init {
        usage = "/permissions group create <name>"
        description = "Criar um grupo de permissões"
    }
}