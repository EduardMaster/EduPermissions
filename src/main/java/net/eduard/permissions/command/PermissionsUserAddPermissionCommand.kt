package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.hybrid.PlayerUser
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

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
        val user = manager.getPlayer(PlayerUser(nome))


        user.addPermission(perm, !perm.startsWith("-"))
        sender.sendMessage(
            PermsMessages.message("player-add-permission")
                .replace("\$player", "" + nome).replace("\$permission", perm)
        )

    }


    init {
        usage = "/permissions user addpermission <player> <permission>"
        description = "Adicionar uma permissão para o jogador"
    }
}