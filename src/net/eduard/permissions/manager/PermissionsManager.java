package net.eduard.permissions.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import net.eduard.api.lib.game.FakePlayer;
import net.eduard.api.lib.storage.Storable;

/**
 * Controlador de Permissões e Grupos dos Jogadores
 * 
 * @author Eduard-PC
 *
 */
public class PermissionsManager {

	@Storable.StorageAttributes(reference = true)
	private PermissionsGroup groupDefault;

	private List<PermissionsGroup> groups = new ArrayList<>();

	private List<PermissionsPlayer> players = new ArrayList<>();

	public PermissionsPlayer getPlayer(Player player) {
		return getPlayer(new FakePlayer(player));
	}
	public void removeGroup(PermissionsGroup group) {
		for (PermissionsPlayer user : players) {
			user.getGroups().remove(group);
		}
		groups.remove(group);
	}
	public PermissionsGroup getGroup(String name) {
		for (PermissionsGroup group : groups) {
			if (group.getName().equalsIgnoreCase(name)) {
				return group;
			}
		}
		return null;
	}

	public PermissionsGroup createGroup(String name, String prefix, String suffix, String... permissoes) {
		PermissionsGroup group = new PermissionsGroup();
		group.setName(name);
		group.getPermissions().addAll(Arrays.asList(permissoes));
		groups.add(group);
		return group;
	}

	public PermissionsPlayer getPlayer(FakePlayer player) {
		for (PermissionsPlayer playerRegistred : players) {
			if (playerRegistred.getPlayer().equals(player)) {
				return playerRegistred;
			}
		}
		return null;

	}


	public List<PermissionsPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<PermissionsPlayer> players) {
		this.players = players;
	}

	public List<PermissionsGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<PermissionsGroup> groups) {
		this.groups = groups;
	}

	public PermissionsGroup getGroupDefault() {
		return groupDefault;
	}

	public void setGroupDefault(PermissionsGroup groupDefault) {
		this.groupDefault = groupDefault;
	}

	public PermissionsPlayer createPlayer(FakePlayer fakePlayer, String prefix, String suffix, String... permissions) {

		PermissionsPlayer player = new PermissionsPlayer();
		player.setPlayer(fakePlayer);
		player.getGroups().add(groupDefault);
		player.getPermissions().addAll(Arrays.asList(permissions));
		player.setPrefix(prefix);
		player.setSuffix(suffix);
		players.add(player);
		return player;

	}

}
