package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.command.Sender
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsUserAddGroupCommand :
    Command("addgroup", "adicionargrupo") {

    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val grupo = args[3]
        val user = manager.getPlayer(PlayerOffline(nome))
        val group = manager.getGroup(grupo)
        if (group == null) {
            sender.sendMessage(
                PermsMessages.message("group-not-exists")
                    .replace("\$group", "" + grupo)
            )
            return
        }
        user.addGroup(group)
        sender.sendMessage(
            PermsMessages.message("player-add-group")
                .replace("\$player", "" + nome)
                .replace("\$group", grupo)
        )

    }


    init {
        usage = "/permissions user addgroup <player> <group>"
        description = "Adicionar um grupo para o jogador"
    }
}