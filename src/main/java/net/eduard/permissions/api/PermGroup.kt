package net.eduard.permissions.api


interface PermGroup {


    var name : String
    fun getPermissions() : Map<String,Boolean>
    fun addPermission(permission : String)
    fun removePermission(permission : String)
    fun hasPermission(permission : String)







}