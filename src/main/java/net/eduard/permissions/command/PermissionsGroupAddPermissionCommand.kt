package net.eduard.permissions.command

import net.eduard.api.lib.command.Command
import net.eduard.api.lib.command.Sender

import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsMessages

class PermissionsGroupAddPermissionCommand :
    Command("addpermission", "adicionarpermissao") {


    override fun onCommand(sender: Sender, args: List<String>) {
        val manager = PermissionsAPI.instance
        if (args.size < 4) {
            sendUsage(sender)
            return
        }
        val nome = args[2]
        val group = manager.getGroup(nome)
        val perm = args[3]
        if (group != null) {
            group.addPermission(perm,!perm.startsWith("-"))
            sender.sendMessage(
                PermsMessages.message("group-add-permission")
                    .replace("\$group", "" + nome)
                    .replace("\$permission", perm)
            )
            return
        }
        sender.sendMessage(
            PermsMessages.message("group-not-exists")
                .replace("\$group", "" + nome)
        )


        //

    }


    init {
        usage = "/permissions group addpermission <group> <permission>"
        description = "Adicionar permissao para o grupo"
    }
}