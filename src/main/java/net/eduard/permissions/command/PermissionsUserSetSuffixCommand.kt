package net.eduard.permissions.command

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.manager.CommandManager
import net.eduard.api.lib.modules.Mine
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermMessages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class PermissionsUserSetSuffixCommand :
    CommandManager("setsuffix", "definirsufixo") {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        val manager = PermissionsAPI.getInstance()
        if (args.size < 4) {
            sendUsage(sender)
        } else {
            val nome = args[2]
            val suffixo = Mine.toChatMessage(args[3])
            val user = manager.getPlayer(PlayerOffline(nome))
            user.suffix = suffixo
            sender.sendMessage(
                PermMessages.message("player-set-prefix")
                    .replace("\$player", "" + nome).replace("\$prefix", suffixo)
            )
            //
        }
        return true
    }

    init {
        usage = "/permissions user setsuffix <player> <permission>"
        description = "Definir o sufixo do jogador"
    }
}