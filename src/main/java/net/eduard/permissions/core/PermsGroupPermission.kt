package net.eduard.permissions.core

import net.eduard.api.lib.database.SQLManager
import net.eduard.api.lib.database.annotations.*
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.permissions.api.PermissionsAPI

@TableName("perm_group_permissions")
class PermsGroupPermission : DatabaseElement {

    override val sqlManager: SQLManager
        get() = PermissionsAPI.sqlManager
    @ColumnPrimary
    var id = 0
    @ColumnRelation
    @ColumnName("group_id")
    lateinit var group : PermsGroup
    @ColumnSize(100)
    var permission = "permissao"
    var state = true
}