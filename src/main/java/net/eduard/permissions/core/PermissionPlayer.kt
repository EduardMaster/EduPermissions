package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.storage.Storable
import net.eduard.permissions.api.PermGroup
import net.eduard.permissions.api.PermPlayer
import net.eduard.permissions.api.PermissionsAPI

@Storable.StorageAttributes(indentificate = true)
class PermissionPlayer : PermPlayer {


    override var player = PlayerOffline("Eduard")


    override var prefix = ""
    override var suffix = ""
    override val permissions: MutableMap<String, Boolean> = mutableMapOf()
    override fun addGroup(group: PermGroup) {
        groups.add(group as PermissionsGroup)
    }


    @Transient
    override var groups: MutableSet<PermissionsGroup> = linkedSetOf()


    fun save() {
        PermissionsAPI.instance.plugin.storageManager.update(this)
    }


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
            for (piece in 0..split.size - 2) {
                tempPerm += piece
                if (permissions.containsKey(tempPerm.toLowerCase() + ".*")) {
                    return true
                }
                tempPerm += "."
            }
        }

        for (group in groups) {
            if (group.hasPermission(permissionName)) {
                return true
            }
        }
        return false
    }

    override fun delete() {

        reset()


    }

    fun canRemove(): Boolean {

        if (permissions.isEmpty()
            && groups.size == 1 && hasGroup(PermissionsAPI.instance.groupDefault)
        )
            return true


        return false
    }


}