package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.plugin.IPlugin
import net.eduard.permissions.api.PermGroup
import net.eduard.permissions.api.PermissionsAPI

/**
 * Controlador de Permissões e Grupos dos Jogadores
 *
 * @author Eduard
 */
class PermsManager : PermissionsAPI {


    override lateinit var groupDefault: PermGroup


    override lateinit var plugin: IPlugin
     fun changeDefaultGroup(group: PermsGroup){
       groupDefault = group
    }
    override var players = mutableMapOf<PlayerOffline, PermsPlayer>()
    override var groups = mutableMapOf<String, PermsGroup>()

    override fun registerGroup(group: PermGroup) {

        groups[group.name.toLowerCase()] = group as PermsGroup
    }

    override fun unregisterGroup(group: PermGroup) {
        for (user in players.values) {
            user.removeGroup(group)
        }
        groups.remove(group.name.toLowerCase())
        group.delete()
    }

    override fun getGroup(groupName: String): PermsGroup? {
        return groups[groupName.toLowerCase()]
    }

    override fun reload() {
        val sqlManager = PermissionsAPI.sqlManager
        if (sqlManager.hasConnection()) {
            players.clear()
            groups.clear()
            sqlManager.createTable(PermsGroup::class.java)
            sqlManager.createTable(PermsGroupPermission::class.java)
            sqlManager.createTable(PermsGroupChild::class.java)

            sqlManager.createTable(PermsPlayer::class.java)
            sqlManager.createTable(PermsPlayerGroup::class.java)
            sqlManager.createTable(PermsPlayerPermission::class.java)

            sqlManager.createReferences(PermsGroup::class.java)
            sqlManager.createReferences(PermsGroupChild::class.java)
            sqlManager.createReferences(PermsGroupPermission::class.java)

            sqlManager.createReferences(PermsPlayer::class.java)
            sqlManager.createReferences(PermsPlayerGroup::class.java)
            sqlManager.createReferences(PermsPlayerPermission::class.java)
            val users = sqlManager.getAllData(PermsPlayer::class.java)
            for (user in users){
                players[user.player] = user
            }
            val listGroup = sqlManager.getAllData(PermsGroup::class.java)
            for (group in listGroup) {
                groups[group.name.toLowerCase()] = group
            }
            sqlManager.updateReferences()
            for (permOfTheGroup in sqlManager.getAllData(PermsGroupPermission::class.java)){
                permOfTheGroup.group.groupPermissions.add(permOfTheGroup)
                permOfTheGroup.group.permissions[permOfTheGroup.permission.toLowerCase()] = permOfTheGroup.state
            }
            for (childOfTheGroup in sqlManager.getAllData(PermsGroupChild::class.java)){
                childOfTheGroup.group.groupChildren.add(childOfTheGroup)
                childOfTheGroup.group.children.add(childOfTheGroup.groupChild)
            }

            for (permOfThePlayer in sqlManager.getAllData(PermsPlayerPermission::class.java)){
                permOfThePlayer.player.playerPermissions.add(permOfThePlayer)
                permOfThePlayer.player.permissions[permOfThePlayer.permission.toLowerCase()] = permOfThePlayer.state
            }
            for (groupOfThePlayer in sqlManager.getAllData(PermsPlayerGroup::class.java)){
                groupOfThePlayer.player.playerGroups.add(groupOfThePlayer)
                groupOfThePlayer.player.groups.add(groupOfThePlayer.group)
            }
        }

    }

    override fun createGroup(
        name: String,
        prefix: String,
        suffix: String,
        vararg permissions: String
    ): PermsGroup {
        val group = PermsGroup()
        group.name = name
        group.insert()
        for (perm in permissions) {
            group.addPermission(perm, true)
        }
        group.update()
        groups[group.name.toLowerCase()] = group
        return group
    }

    override fun unregisterAllGroups() {

    }

    override fun getPlayer(playerName: String): PermsPlayer {
        return getPlayer(PlayerOffline(playerName))
    }

    override fun getPlayer(offline: PlayerOffline): PermsPlayer {
        var conta = players[offline]
        if (conta==null){
            conta = newAccount(offline)
        }
        return conta
    }

    fun newAccount(offline: PlayerOffline): PermsPlayer {
        val player = PermsPlayer()
        player.player = offline

        player.insert()
        players[offline] = player

        player.addGroup(groupDefault)
        return player
    }


}