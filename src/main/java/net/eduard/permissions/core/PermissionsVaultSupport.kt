package net.eduard.permissions.core

import net.eduard.permissions.EduPermissions
import net.milkbowl.vault.permission.Permission

class PermissionsVaultSupport : Permission() {
    override fun getGroups(): Array<String> {
        return arrayOf()
    }

    override fun getName(): String {
        return EduPermissions.instance.description.name
    }

    override fun getPlayerGroups(
        playerName: String,
        playerWorld: String
    ): Array<String> {
        return arrayOf()
    }

    override fun getPrimaryGroup(arg0: String, arg1: String): String {
        return ""
    }

    override fun groupAdd(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun groupHas(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun groupRemove(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun hasGroupSupport(): Boolean {
        return false
    }

    override fun hasSuperPermsCompat(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    }

    override fun playerAdd(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun playerAddGroup(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun playerHas(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun playerInGroup(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun playerRemove(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }

    override fun playerRemoveGroup(arg0: String, arg1: String, arg2: String): Boolean {
        return false
    }
}