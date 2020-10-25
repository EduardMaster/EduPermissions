package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermGroup
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermMessages

class PermissionsUserAddPermissionCommand :
    Command("addpermission", "adicionarpermissao") {


    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val perm = args[3]
        val user = manager.getPlayer(PlayerOffline(nome))


        user.addPermission(perm, !perm.startsWith("-"))
        sender.sendMessage(
            PermMessages.message("player-add-permission")
                .replace("\$player", "" + nome).replace("\$permission", perm)
        )

    }


    init {
        usage = "/permissions user addpermission <player> <permission>"
        description = "Adicionar uma permissão para o jogador"
    }
}