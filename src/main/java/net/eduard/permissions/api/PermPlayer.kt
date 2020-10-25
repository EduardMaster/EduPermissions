package net.eduard.permissions.api

import net.eduard.api.lib.command.PlayerOffline

interface PermPlayer {

    var player : PlayerOffline
    var prefix : String
    var suffix : String
    val groups : MutableSet<out PermGroup>
    val permissions : MutableMap<String,Boolean>
    fun hasGroup(group : PermGroup): Boolean {
        return groups.contains(group)
    }
    fun addGroup( group : PermGroup)

    fun removeGroup(group : PermGroup){
        groups.remove(group)
    }
    fun addPermission(permission : String, flag : Boolean){
        permissions[permission.toLowerCase()] = flag
    }
    fun removePermission(permission : String){
        permissions.remove(permission.toLowerCase())
    }
    fun hasPermission(permission : String): Boolean {
        return permission.contains(permission.toLowerCase())
    }
    fun delete()
    fun reset(){
        permissions.clear()
        groups.clear()
    }




}