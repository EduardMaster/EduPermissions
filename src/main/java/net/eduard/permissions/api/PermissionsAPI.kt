package net.eduard.permissions.api

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.plugin.IPlugin
import net.eduard.permissions.core.PermsManager

interface PermissionsAPI {
    companion object {


        @JvmStatic
        lateinit var instance: PermsManager
        val sqlManager get() = instance.plugin.sqlManager


    }


    var plugin : IPlugin
    fun reload()
    val groupDefault : PermGroup
    val groups: MutableMap<String,out PermGroup>
    val players: MutableMap<PlayerOffline,out PermPlayer>
    fun registerGroup(group: PermGroup)
    fun unregisterGroup(group: PermGroup)
    fun createGroup(
        name: String,
        prefix: String,
        suffix: String,
        vararg permissions: String
    ): PermGroup

    fun unregisterAllGroups()
    fun getPlayer(playerName: String): PermPlayer
    fun getGroup(groupgName : String) : PermGroup
    fun getPlayer(player: PlayerOffline): PermPlayer



}