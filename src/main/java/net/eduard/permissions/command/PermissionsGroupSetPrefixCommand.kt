package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.api.lib.modules.Mine
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsGroupSetPrefixCommand :

    Command("setprefix", "definirprefixo") {


    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val prefixo =Mine.toChatMessage(args[3])
        val group = manager.getGroup(nome)
        if (group != null) {
            group.prefix = prefixo
            group.update()
            sender.sendMessage(
                PermsMessages.message("group-set-prefix").replace("\$group", "" + nome)
                    .replace("\$prefix", prefixo)
            )
        } else {
            sender.sendMessage(
                PermsMessages.message("group-not-exists").replace("\$group", "" + nome)
            )
        }
        //
    }


    init {
        usage = "/permissions group setprefix <group> <prefix>"
        description = "Definir o prefixo do grupo"
    }
}