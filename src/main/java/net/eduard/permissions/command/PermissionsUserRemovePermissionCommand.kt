package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.hybrid.PlayerUser
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsUserRemovePermissionCommand :
    Command("removepermission", "removerpermissao") {
    override fun onCommand(
        sender: Sender,
        args: List<String>
    ) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
        } else {
            val nome = args[2]
            val perm = args[3]
            val user = manager.getPlayer(PlayerUser(nome))

            user.removePermission(perm)
            sender.sendMessage(
                PermsMessages.message("player-remove-permission")
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