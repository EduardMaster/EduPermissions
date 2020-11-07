package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.hybrid.PlayerUser
import net.eduard.api.lib.command.Sender
import lib.modules.Mine
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsUserSetPrefixCommand :
    Command("setprefix", "definirprefixo") {
    override fun onCommand(
        sender: Sender,
        args: List<String>
    ) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val prefixo = lib.modules.Mine.toChatMessage(args[3])
        val user = manager.getPlayer(PlayerUser(nome))

        user.suffix = prefixo
        user.update()
        sender.sendMessage(
            PermsMessages.message("player-set-prefix")
                .replace("\$player", "" + nome).replace("\$prefix", prefixo)
        )
    }

    init {
        usage = "/permissions user setprefix <player> <permission>"
        description = "Definir o prefixo do jogador"
    }
}