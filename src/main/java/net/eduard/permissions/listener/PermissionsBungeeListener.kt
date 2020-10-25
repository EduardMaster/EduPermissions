package net.eduard.permissions.listener

import net.eduard.api.lib.command.PlayerBungee
import net.eduard.permissions.api.PermissionsAPI
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.PermissionCheckEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

class PermissionsBungeeListener : Listener {

    @EventHandler
    fun event(e : PermissionCheckEvent){
        if (e.sender is ProxiedPlayer) {

            val player = e.sender as ProxiedPlayer
            val offline = PlayerBungee(player).offline
            val user = PermissionsAPI.instance
                .getPlayer(offline)
            e.setHasPermission(user.hasPermission(e.permission))
        }
    }

}
