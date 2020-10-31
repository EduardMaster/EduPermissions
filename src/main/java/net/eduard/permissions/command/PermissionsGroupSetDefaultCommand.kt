package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsGroupSetDefaultCommand :
    Command("setdefault", "definirpadrao") {
    override fun onCommand(
        sender: Sender,
        args: List<String>
    ) {
        val manager = PermissionsAPI.instance
        if (args.size < 3) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val group = manager.getGroup(nome)
        manager.changeDefaultGroup(group)
        sender.sendMessage(
            PermsMessages.message("group-set-default")
                .replace("\$group", "" + nome)
        )


    }

    init {
        usage = "/permissions group setdefault <group>"
        description = "Definir o grupo padrão do sistema"
    }
}