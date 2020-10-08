package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.EduPermissions
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermMessages

class PermissionsUserRemovePermissionCommand :
    Command("removepermission", "removerpermissao") {
    override fun onCommand(
        sender: Sender,
        args: List<String>
    ) {
        val manager = PermissionsAPI.getInstance()
        if (args.size < 4) {
            sendUsage(sender)
        } else {
            val nome = args[2]
            val perm = args[3]
            val user = manager.getPlayer(PlayerOffline(nome))

            user.permissions.remove(perm)
            sender.sendMessage(
                PermMessages.message("player-remove-permission")
                    .replace("\$player", "" + nome)
                    .replace("\$permission", perm)
            )

        }
    }

    init {
        usage = "/permissions user removepermission <player> <permission>"
        description = "Remover uma permissão para o jogador"
    }
}