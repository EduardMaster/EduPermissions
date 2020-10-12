package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.database.StorageManager
import net.eduard.api.lib.storage.Storable
import net.eduard.permissions.EduPermissions
import net.eduard.permissions.api.PermissionsAPI

@Storable.StorageAttributes(indentificate = true)
class PermissionsPlayer {

    @StorageManager.StoreKey
    var player = PlayerOffline("Eduard")

    @Transient
    lateinit var editor: PermissionsPlayerEditor
    var prefix = ""
    var suffix = ""
    var permissions = mutableListOf<String>()

    @Transient
    var groups = mutableListOf<PermissionsGroup>()
    var groupsNames = mutableListOf<String>()

    fun save() {
         EduPermissions.instance.storageManager
             .update(this)
    }


    fun hasPermission(permissionName: String): Boolean {
        for (perm in permissions) {
            if (perm == "*") {
                return true
            }
            if (perm.endsWith(".*")) {
                val permCortada = perm.replace(".*", "")
                if (permissionName.startsWith(permCortada)) {
                    return true
                }
            }
        }
        for (group in groups) {
            for (perm in group.permissions) {
                if (perm == "*") {
                    return true
                }
                if (perm.endsWith(".*")) {
                    val permCortada = perm.replace(".*", "")
                    if (permissionName.startsWith(permCortada)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun canRemove(): Boolean {

        if (permissions.isEmpty()
            &&  groups.size == 1 && groupsNames.
            contains(PermissionsAPI.getInstance().groupDefault))
            return true


            return false
    }


}