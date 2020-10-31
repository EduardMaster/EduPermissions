package net.eduard.permissions.core

import net.eduard.api.lib.database.SQLManager
import net.eduard.api.lib.database.annotations.ColumnName
import net.eduard.api.lib.database.annotations.ColumnPrimary
import net.eduard.api.lib.database.annotations.ColumnRelation
import net.eduard.api.lib.database.annotations.TableName
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.permissions.api.PermissionsAPI

@TableName("perm_group_children")
class PermsGroupChild : DatabaseElement {

    override val sqlManager: SQLManager
        get() = PermissionsAPI.sqlManager
    @ColumnPrimary
    var id = 0
    @ColumnRelation
    @ColumnName("group_id")
    lateinit var group : PermsGroup

    @ColumnRelation
    @ColumnName("child_id")
    lateinit var groupChild : PermsGroup
}