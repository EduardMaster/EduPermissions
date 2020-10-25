package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.EduPermissions
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermMessages

class PermissionsGroupDeleteCommand :
    Command("delete", "deletar") {

    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 3) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val group = manager.getGroup(nome)
        if (group != null) {
            manager.unregisterGroup(group)
            sender.sendMessage(
                PermMessages.message("group-deleted")
                    .replace("\$group", "" + nome)
            )
            return
        }
        sender.sendMessage(
           PermMessages
                .message("group-not-exists")
                .replace("\$group", name))


    }


    init {
        usage = "/permissions group delete <group>"
        description = "Deletar um grupo de permissões"
    }
}