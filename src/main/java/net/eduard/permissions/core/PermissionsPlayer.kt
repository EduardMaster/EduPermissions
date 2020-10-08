package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.storage.Storable

@Storable.StorageAttributes(indentificate = true)
class PermissionsPlayer {
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


}