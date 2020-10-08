package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.EduPermissions

class PermissionsGroupSetDefaultCommand :
    Command("setdefault", "definirpadrao") {
    override fun onCommand(
        sender: Sender,
        args: List<String>
    ) {
        val manager = EduPermissions.instance.manager
        if (args.size < 3) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val group = manager.getGroup(nome)
        if (group != null) {
            manager.setDefaultGroup(group)
            sender.sendMessage(
                EduPermissions.instance.message("group-set-default").replace("\$group", "" + nome)
            )
        } else {
            sender.sendMessage(
                EduPermissions.instance.message("group-not-exists").replace("\$group", "" + nome)
            )
        }


    }

    init {
        usage = "/permissions group setdefault <group>"
        description = "Definir o grupo padrão do sistema"
    }
}