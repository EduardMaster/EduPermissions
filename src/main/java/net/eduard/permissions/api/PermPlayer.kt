package net.eduard.permissions.api

import net.eduard.api.lib.command.PlayerOffline

interface PermPlayer {

    var player : PlayerOffline
    var prefix : String
    var suffix : String
    val groups : MutableSet<out PermGroup>
    val permissions : MutableMap<String,Boolean>
    fun hasGroup(group : PermGroup): Boolean
    fun addGroup( group : PermGroup)
    fun removeGroup(group : PermGroup)
    fun addPermission(permission : String, flag : Boolean)
    fun removePermission(permission : String)
    fun hasPermission(permission : String): Boolean
    fun delete()
    fun reset()




}