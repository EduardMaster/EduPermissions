package net.eduard.permissions.api

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.plugin.IPlugin
import net.eduard.permissions.core.PermissionsGroup
import net.eduard.permissions.core.PermissionsManager

interface PermissionsAPI {
    companion object {
        @JvmStatic
        lateinit var instance: PermissionsAPI



    }


    var plugin : IPlugin
    var groupDefault : PermGroup
    val groups: MutableMap<String,out PermGroup>
    val players: MutableMap<PlayerOffline,out PermPlayer>
    fun registerGroup(group: PermGroup)

    fun unregisterGroup(group: PermGroup) {
        groups.remove(group.name.toLowerCase())
    }
    fun createGroup(
        name: String,
        prefix: String,
        suffix: String,
        vararg permissions: String
    ): PermGroup

    fun unregisterAllGroups() {
        for (group in groups.values){
            group.children.clear()
        }
        for (user in players.values){
           user.groups.clear()
        }
        groups.clear()
    }

    fun getPlayer(playerName: String): PermPlayer {
        return getPlayer(PlayerOffline(playerName))
    }
    fun getGroup(groupgName : String) : PermGroup{
        return groups[groupgName.toLowerCase()] as PermGroup
    }

    fun getPlayer(player: PlayerOffline): PermPlayer


}