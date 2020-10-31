package net.eduard.permissions.listener

import net.eduard.api.lib.command.PlayerBukkit
import net.eduard.api.lib.command.offline
import net.eduard.api.lib.manager.EventsManager
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.core.PermsPlayerEditor
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class PermissionsListener : EventsManager() {

    @EventHandler
    fun join(e : PlayerJoinEvent){
        val offline = PlayerBukkit(e.player).offline
        e.player.sendMessage(""+offline)
        val user = PermissionsAPI.instance
            .getPlayer(offline)
        PermsPlayerEditor(e.player, user)


    }
}