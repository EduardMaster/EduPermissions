package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender
import net.eduard.api.lib.modules.Mine
import net.eduard.permissions.EduPermissions
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermMessages

class PermissionsGroupSetSuffixCommand :
    Command("setsuffix", "definirsufixo") {
    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.getInstance()
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val suffixo = Mine.toChatMessage(args[3])
        val group = manager.getGroup(nome)
        if (group != null) {
            group.suffix = suffixo
            sender.sendMessage(
                PermMessages.message("group-set-suffix")
                    .replace("\$group", "" + nome)
                    .replace("\$suffix", suffixo)
            )
        } else {
            sender.sendMessage(
                PermMessages.message("group-not-exists")
                    .replace("\$group", "" + nome)
            )
        }
        //

    }


    init {
        usage = "/permissions group setsuffix <group> <suffix>"
        description = "Definir o sufixo do grupo"
    }
}