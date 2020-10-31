package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.permissions.EduPermissions
import net.eduard.permissions.api.PermissionsAPI
import net.milkbowl.vault.permission.Permission

class PermsVaultSupport : Permission() {
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
        return PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
            .groups.map{it.name}.toTypedArray()
    }

    override fun getPrimaryGroup(playerName: String, worldName: String): String {

        return PermissionsAPI.instance.getPlayer(PlayerOffline(playerName)).groups.first().name
    }

    override fun groupAdd(groupName: String, worldName: String, permission: String): Boolean {

        val group = PermissionsAPI.instance.getGroup(groupName)!!


         group.addPermission(permission,true)
        return true
    }

    override fun groupHas(groupName: String, worldName: String, permission: String): Boolean {
        val group = PermissionsAPI.instance.getGroup(groupName)!!
        return group.permissions.contains(permission)

    }

    override fun groupRemove(groupName: String, worldName: String, permission: String): Boolean {

        val group = PermissionsAPI.instance.getGroup(groupName) ?: return false
        group.removePermission(permission)

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
        val user = PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
        user.addPermission(permission,true)

        return true
    }

    override fun playerAddGroup(playerName: String, worldName: String, groupName: String): Boolean {
        val user = PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
        val group = PermissionsAPI.instance.getGroup(groupName)!!
        user.addGroup(group)
        return true
    }

    override fun playerHas(playerName: String, worldName: String, permission: String): Boolean {
        val user = PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
        return user.permissions.contains(permission)
    }

    override fun playerInGroup(playerName: String, worldName: String, groupName: String): Boolean {
        val user = PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
        val group = PermissionsAPI.instance.getGroup(groupName)!!
        return user.hasGroup(group)
    }

    override fun playerRemove(playerName: String, worldName: String, permission: String): Boolean {
        val user = PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
        user.removePermission(permission)
        return true
    }

    override fun playerRemoveGroup(playerName: String, worldName: String, groupName: String): Boolean {
        val user = PermissionsAPI.instance.getPlayer(PlayerOffline(playerName))
        val group = PermissionsAPI.instance.getGroup(groupName)!!
        user.removeGroup(group)

        return true
    }
}