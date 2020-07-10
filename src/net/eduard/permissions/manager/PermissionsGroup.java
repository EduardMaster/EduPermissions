package net.eduard.permissions.manager;

import net.eduard.api.lib.storage.Storable;

import java.util.ArrayList;
import java.util.List;

public class PermissionsGroup {

	private String name;
	
	@Storable.StorageAttributes
	private List<PermissionsGroup> childrens = new ArrayList<>();
	private List<String> permissions = new ArrayList<>();
	
	private String prefix;
	private String suffix;
	
	
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<PermissionsGroup> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<PermissionsGroup> childrens) {
		this.childrens = childrens;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
