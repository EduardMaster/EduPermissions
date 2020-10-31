package net.eduard.permissions.core

import net.eduard.api.lib.database.SQLManager
import net.eduard.api.lib.database.annotations.ColumnName
import net.eduard.api.lib.database.annotations.ColumnPrimary
import net.eduard.api.lib.database.annotations.ColumnRelation
import net.eduard.api.lib.database.annotations.TableName
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.permissions.api.PermissionsAPI

@TableName("perm_player_groups")
class PermsPlayerGroup : DatabaseElement {

    override val sqlManager: SQLManager
        get() = PermissionsAPI.sqlManager
    @ColumnPrimary
    var id = 0
    @ColumnName("player_id")
    @ColumnRelation
    lateinit var player : PermsPlayer
    @ColumnName("group_id")
    @ColumnRelation
    lateinit var group : PermsGroup
}