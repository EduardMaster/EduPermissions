package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.command.Sender
import net.eduard.api.lib.modules.Mine
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsUserSetSuffixCommand :
    Command("setsuffix", "definirsufixo") {

    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
        } else {
            val nome = args[2]
            val suffixo = Mine.toChatMessage(args[3])
            val user = manager.getPlayer(PlayerOffline(nome))
            user.suffix = suffixo
            sender.sendMessage(
                PermsMessages.message("player-set-prefix")
                    .replace("\$player", "" + nome).replace("\$prefix", suffixo)
            )
            //
        }
    }


    init {
        usage = "/permissions user setsuffix <player> <permission>"
        description = "Definir o sufixo do jogador"
    }
}