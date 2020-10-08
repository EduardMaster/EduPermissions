package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.command.Sender
import net.eduard.api.lib.modules.Mine
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermMessages

class PermissionsUserSetPrefixCommand :
    Command("setprefix", "definirprefixo") {
    override fun onCommand(
        sender: Sender,
        args: List<String>
    ) {
        val manager = PermissionsAPI.getInstance()
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val prefixo = Mine.toChatMessage(args[3])
        val user = manager.getPlayer(PlayerOffline(nome))

//			PermissionsGroup group = task.getGroup(nome);
        //sender.sendMessage(
        //		EduPermissions.instance.message("player-not-exists")
        //				.replace("$player", "" + nome));
        user.suffix = prefixo
        sender.sendMessage(
            PermMessages.message("player-set-prefix")
                .replace("\$player", "" + nome).replace("\$prefix", prefixo)
        )
    }

    init {
        usage = "/permissions user setprefix <player> <permission>"
        description = "Definir o prefixo do jogador"
    }
}