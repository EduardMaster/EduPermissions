package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.database.SQLManager
import net.eduard.api.lib.database.annotations.ColumnPrimary
import net.eduard.api.lib.database.annotations.ColumnSize
import net.eduard.api.lib.database.annotations.TableName
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.permissions.api.PermGroup
import net.eduard.permissions.api.PermPlayer
import net.eduard.permissions.api.PermissionsAPI

@TableName("perm_players")
class PermsPlayer : PermPlayer , DatabaseElement {

    override val sqlManager: SQLManager
        get() = PermissionsAPI.sqlManager

    @ColumnPrimary
    var id = 0
    @ColumnSize(16)
    override var player = PlayerOffline("Eduard")
    override var prefix = ""
    override var suffix = ""
    @Transient
    override val permissions: MutableMap<String, Boolean> = mutableMapOf()
    @Transient
    override var groups: MutableSet<PermsGroup> = linkedSetOf()

    @Transient
    var playerPermissions = mutableListOf<PermsPlayerPermission>()

    @Transient
    var playerGroups = mutableListOf<PermsPlayerGroup>()

    private fun getPerm(perm: String): PermsPlayerPermission? =
        playerPermissions.firstOrNull { it.permission.equals(perm, true) }

    private fun getGroup(group : PermsGroup ) : PermsPlayerGroup? {
        return playerGroups.firstOrNull { it.group.name.equals(group.name,true) }
    }


    private fun createPerm(perm : String)
            : PermsPlayerPermission{
        val permOfTheGroup = PermsPlayerPermission()
        permOfTheGroup.player = this
        permOfTheGroup.permission = perm
        permOfTheGroup.insert()
        permissions[perm.toLowerCase()] = true


        return permOfTheGroup
    }
    private fun createGroupLink(group : PermsGroup)
            : PermsPlayerGroup{
        val childOfTheGroup = PermsPlayerGroup()
        childOfTheGroup.player = this
        childOfTheGroup.group = group
        childOfTheGroup.insert()
        return childOfTheGroup
    }
    override fun addGroup(group: PermGroup) {
        if (hasGroup(group))return
        val groupLink = createGroupLink(group as PermsGroup)
        playerGroups.add(groupLink)
        groups.add(group as PermsGroup)
    }

    override fun removeGroup(group: PermGroup) {
        val playerGroup = getGroup(group as PermsGroup)
        if (playerGroup!= null){
            playerGroup.delete()
            groups.remove(group)
            playerGroups.remove(playerGroup)
        }
    }

    override fun removePermission(permission: String) {
        val perm = getPerm(permission)
        if (perm != null) {
            perm.delete()
            permissions.remove(permission)
            playerPermissions.remove(perm)
        }
    }

    override fun addPermission(permission: String, flag: Boolean) {
        var perm = getPerm(permission)
        if (perm == null) {
            perm=createPerm(permission)
        }
        perm.permission = permission
        perm.state = flag
        permissions[permission.toLowerCase()] = flag
        perm.update()
    }

    override fun hasGroup(group: PermGroup): Boolean {
       return groups.contains(group)
    }

    override fun reset() {
        // precisa deletar todas permissiones do jogador de uma vez
        for (playerPerm in playerPermissions){
            // pois deletando uma perm por vez será muito lagado
            playerPerm.delete()
        }
        for (playerGroup in playerGroups){
            playerGroup.delete()
        }
        playerGroups.clear()
        groups.clear()
        permissions.clear()
        playerPermissions.clear()
    }


    override fun hasPermission(permission: String): Boolean {
        if (permissions.containsKey("*")) {
            return true
        }
        if (permissions.containsKey(permission.toLowerCase())) {
            return true
        }
        if (permission.contains(".")) {
            val split = permission.split("\\.".toRegex())
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
            if (group.hasPermission(permission)) {
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