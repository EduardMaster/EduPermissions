package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.permissions.EduPermissions
import net.eduard.permissions.api.PermissionsAPI
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
        return PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName)).groupsNames.toTypedArray()
    }

    override fun getPrimaryGroup(playerName: String, worldName: String): String {

        return PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName)).groups[0].name
    }

    override fun groupAdd(groupName: String, worldName: String, permission: String): Boolean {

        val group = PermissionsAPI.getInstance().getGroup(groupName)
        if (group == null) return false


        return group.permissions.add(permission)
    }

    override fun groupHas(groupName: String, worldName: String, permission: String): Boolean {
        val group = PermissionsAPI.getInstance().getGroup(groupName)
        if (group == null) return false
        return group.permissions.contains(permission)

    }

    override fun groupRemove(groupName: String, worldName: String, permission: String): Boolean {

        val group = PermissionsAPI.getInstance().getGroup(groupName)
        if (group == null) return false
        group.permissions.remove(permission)

        return true
    }

    override fun hasGroupSupport(): Boolean {
        return true
    }

    override fun hasSuperPermsCompat(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun playerAdd(playerName: String, worldName: String, permission: String): Boolean {
        val user = PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName))
        return user.permissions.add(permission)
    }

    override fun playerAddGroup(playerName: String, worldName: String, groupName: String): Boolean {
        val user = PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName))
        val group = PermissionsAPI.getInstance().getGroup(groupName)
        if (group == null) return false
        return user.groups.add(group)
    }

    override fun playerHas(playerName: String, worldName: String, permission: String): Boolean {
        val user = PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName))
        return user.permissions.contains(permission)
    }

    override fun playerInGroup(playerName: String, worldName: String, groupName: String): Boolean {
        val user = PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName))
        val group = PermissionsAPI.getInstance().getGroup(groupName)
        if (group == null) return false
        return user.groups.contains(group)
    }

    override fun playerRemove(playerName: String, worldName: String, permission: String): Boolean {
        val user = PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName))
        return user.permissions.remove(permission)
    }

    override fun playerRemoveGroup(playerName: String, worldName: String, groupName: String): Boolean {
        val user = PermissionsAPI.getInstance().getPlayer(PlayerOffline(playerName))
        user.groups.removeIf { it.name.equals(groupName, true) }
        user.groupsNames.removeIf { it.equals(groupName, true) }
        return true
    }
}