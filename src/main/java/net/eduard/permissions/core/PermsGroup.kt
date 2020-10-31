package net.eduard.permissions.core

import net.eduard.api.lib.database.SQLManager
import net.eduard.api.lib.database.annotations.ColumnPrimary
import net.eduard.api.lib.database.annotations.ColumnSize
import net.eduard.api.lib.database.annotations.TableName
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.permissions.api.PermGroup
import net.eduard.permissions.api.PermissionsAPI


@TableName("perm_groups")
class PermsGroup : PermGroup, DatabaseElement {

    @ColumnPrimary
    var id = 0
    @ColumnSize(100)
    override var name = "Grupo"
    override var prefix = ""
    override var suffix = ""




    @Transient
    override var children: MutableSet<PermsGroup> = mutableSetOf()

    @Transient
    override val permissions: MutableMap<String, Boolean> = mutableMapOf()


    @Transient
    var groupPermissions = mutableListOf<PermsGroupPermission>()

    @Transient
    var groupChildren = mutableListOf<PermsGroupChild>()

    private fun getPerm(perm: String): PermsGroupPermission? =
        groupPermissions.firstOrNull { it.permission.equals(perm, true) }

    private fun getChild(groupName : String ) : PermsGroupChild? {
        return groupChildren.firstOrNull { it.groupChild.name.equals(groupName,true) }
    }


    private fun createPerm(perm : String)
            : PermsGroupPermission{
        val permOfTheGroup = PermsGroupPermission()
        permOfTheGroup.group = this
        permOfTheGroup.permission = perm
        permOfTheGroup.insert()
        permissions[perm.toLowerCase()] = true
        groupPermissions.add(permOfTheGroup)

        return permOfTheGroup
    }
    private fun createChild(child : PermsGroup)
            : PermsGroupChild{
        val childOfTheGroup = PermsGroupChild()
        childOfTheGroup.group = this
        childOfTheGroup.groupChild = this
        childOfTheGroup.insert()
        children.add(child)
        return childOfTheGroup
    }

    override fun addChild(child: PermGroup) {
        val groupChild = getChild(child.name)
        if (groupChild == null) {
           createChild(child as PermsGroup)
        }

    }

    override fun removeChild(child: PermGroup) {
        val groupChild = getChild(child.name)
        if (groupChild != null) {
            groupChild.delete()
            children.remove(child)
            groupChildren.remove(groupChild)
        }

    }

    override fun addPermission(permission: String, flag: Boolean) {
        var perm = getPerm(permission)
        if (perm == null) {
            perm = createPerm(permission)
        }
        perm.permission = permission
        perm.state = flag
        permissions[permission.toLowerCase()] = flag
        perm.update()

    }

    override fun removePermission(permission: String) {
        val perm = getPerm(permission)
        if (perm != null) {

            permissions.remove(permission.toLowerCase())
            groupPermissions.remove(perm)
            perm.delete()
        }

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

        for (group in children) {
            if (group.hasPermission(permissionName)) {
                return true
            }
        }
        return false
    }

    override val sqlManager: SQLManager
        get() = PermissionsAPI.sqlManager;


}