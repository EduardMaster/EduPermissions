package net.eduard.permissions.api

import net.eduard.api.lib.command.PlayerOffline

interface PermPlayer {

    var player : PlayerOffline
    var prefix : String
    var suffix : String
    fun getGroups() : List<PermGroup>
    fun getPermissions() : List<String>
    fun hasGroup(group : PermGroup)
    fun addGroup( group : PermGroup)
    fun removeGroup(group : PermGroup)
    fun addPermission(permission : String)
    fun removePermission(permission : String)
    fun hasPermission(permission : String)
    fun delete()
    fun reset()



}