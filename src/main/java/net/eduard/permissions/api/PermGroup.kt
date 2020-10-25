package net.eduard.permissions.api


interface PermGroup {
    var name : String
    var prefix : String
    var suffix : String
    val children : MutableSet<out PermGroup>
    val permissions: MutableMap<String,Boolean>
    fun addPermission(permission : String, flag : Boolean) {
        permissions[permission.toLowerCase()] = flag
    }
    fun removePermission(permission : String){
        permissions.remove(permission.toLowerCase())
    }
    fun hasPermission(permission : String): Boolean {
        return permissions.containsKey(permission.toLowerCase())
    }

}