package net.eduard.permissions.api


interface PermGroup {
    var name : String
    var prefix : String
    var suffix : String
    val children : MutableSet<out PermGroup>
    val permissions: MutableMap<String,Boolean>
    fun addChild(child : PermGroup)
    fun removeChild(child : PermGroup)
    fun addPermission(permission : String, flag : Boolean)
    fun removePermission(permission : String)
    fun hasPermission(permission : String): Boolean

}