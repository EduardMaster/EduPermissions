package net.eduard.permissions.api

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.plugin.IPlugin
import net.eduard.permissions.core.PermissionsManager

interface PermissionsAPI {

    fun getGroups() : List<PermGroup>
    fun getPlayers() : List<PermPlayer>
    fun registerGroup(group : PermGroup)
    fun unregisterGroup(group : PermGroup)
    fun unregisterAllGroups()
    fun getPlayer(playerName : String) : PermPlayer{
        return getPlayer(PlayerOffline(playerName))
    }
    fun getPlayer(player : PlayerOffline) : PermPlayer


    companion object{
        lateinit var manager : PermissionsManager
        lateinit var plugin : IPlugin
        fun getInstance(): PermissionsManager {

            return manager

        }



    }
}