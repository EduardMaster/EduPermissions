package net.eduard.permissions.core

import net.eduard.permissions.api.PermGroup


class PermissionsGroup : PermGroup{
    override var name="Grupo"
    override var prefix = ""
    override var suffix = ""
    override var children: MutableSet<PermissionsGroup> = mutableSetOf()
    override val permissions: MutableMap<String, Boolean> = mutableMapOf()


    override fun hasPermission(permissionName: String): Boolean {
        if (permissions.containsKey("*")) {
            return true
        }
        if (permissions.containsKey(permissionName.toLowerCase())) {
            return true
        }
        if (permissionName.contains(".")) {
            val split = permissionName.split("\\.".toRegex())
            var tempPerm = ""
            for (piece in 0.. split.size-2){
                tempPerm+= piece
                if (permissions.containsKey(tempPerm.toLowerCase()+".*")){
                    return true
                }
                tempPerm+="."
            }
        }

        for (group in children) {
            if (group.hasPermission(permissionName)) {
                return true
            }
        }
        return false
    }


}