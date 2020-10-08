package net.eduard.permissions.core

import net.eduard.api.lib.command.PlayerOffline
import net.eduard.api.lib.modules.FakePlayer
import net.eduard.api.lib.storage.Storable
import org.bukkit.entity.Player
import java.util.*

/**
 * Controlador de Permissões e Grupos dos Jogadores
 *
 * @author Eduard
 */
class PermissionsManager {

    fun setDefaultGroup(group : PermissionsGroup) {
        groupDefault = group.name
    }
    var groupDefault = "Grupo"

    @Transient
    var players = mutableMapOf<PlayerOffline, PermissionsPlayer>()

    @Transient
    var groups = mutableMapOf<String, PermissionsGroup>()


    fun removeGroup(group: PermissionsGroup) {
        for (user in players.values) {
            user.groups.remove(group)
        }
        groups.remove(group.toString().toLowerCase())
    }

    fun getGroup(name: String): PermissionsGroup? {
        return groups[name.toLowerCase()]
    }

    fun createGroup(
        name: String,
        prefix: String,
        suffix: String,
        vararg permissoes: String
    ): PermissionsGroup {
        val group = PermissionsGroup()
        group.name = name
        group.permissions.addAll(Arrays.asList<String>(*permissoes))
        groups[group.name.toLowerCase()] = group
        return group
    }

    fun getPlayer(player: PlayerOffline): PermissionsPlayer {
        return players.getOrDefault(player, newAccount(player))
    }

    fun newAccount(offline: PlayerOffline): PermissionsPlayer {
        val player = PermissionsPlayer()
        player.player = offline
        player.groupsNames.add(groupDefault)
        players.put(offline, player)
        return player
    }


}