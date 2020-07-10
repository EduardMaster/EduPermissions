package net.eduard.permissions.manager;

import java.util.ArrayList;
import java.util.List;

import net.eduard.api.lib.game.FakePlayer;
import net.eduard.api.lib.storage.Storable;

@Storable.StorageAttributes(indentificate = true)
public class PermissionsPlayer {

	private FakePlayer player;
	private transient PermissionsPlayerEditor editor;
	private String prefix;
	private String suffix;

	private List<String> permissions = new ArrayList<>();
	@Storable.StorageAttributes(reference = true)
	private List<PermissionsGroup> groups = new ArrayList<>();

	public List<PermissionsGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<PermissionsGroup> groups) {
		this.groups = groups;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public FakePlayer getPlayer() {
		return player;
	}

	public void setPlayer(FakePlayer player) {
		this.player = player;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public PermissionsPlayerEditor getEditor() {
		return editor;
	}

	public void setEditor(PermissionsPlayerEditor editor) {
		this.editor = editor;
	}

}
