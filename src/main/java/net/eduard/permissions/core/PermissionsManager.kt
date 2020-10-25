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
class PermissionsManager : PermissionsAPI{

    fun setDefaultGroup(group : PermissionsGroup) {
        groupDefault = group
    }
    override lateinit var groupDefault : PermGroup

    @Transient
    override var players = mutableMapOf<PlayerOffline, PermissionPlayer>()

    override fun registerGroup(group: PermGroup) {
        groups[group.name.toLowerCase()] = group as PermissionsGroup
    }

    override lateinit var plugin: IPlugin


    override var groups = mutableMapOf<String, PermissionsGroup>()


    fun removeGroup(group: PermissionsGroup) {
        for (user in players.values) {
            user.groups.remove(group)
        }
        groups.remove(group.toString().toLowerCase())
    }

    override fun getGroup(name: String): PermissionsGroup {
        return groups[name.toLowerCase()]!!
    }

    override fun createGroup(
        name: String,
        prefix: String,
        suffix: String,
        vararg permissions: String
    ): PermissionsGroup {
        val group = PermissionsGroup()
        group.name = name
        for (perm in permissions){
            group.permissions[perm.toLowerCase()] = true
        }

        groups[group.name.toLowerCase()] = group
        return group
    }

    override fun getPlayer(player: PlayerOffline): PermissionPlayer {
        return players.getOrDefault(player, newAccount(player))
    }

    fun newAccount(offline: PlayerOffline): PermissionPlayer {
        val player = PermissionPlayer()
        player.player = offline
        player.groups.add(groupDefault as PermissionsGroup)
        players[offline] = player
        return player
    }


}