
package net.eduard.permissions.command;

import net.eduard.api.lib.manager.CommandManager;

public class PermissionsGroupCommand extends CommandManager {

	public PermissionsGroupCommand() {
		super("group","grupo");
		setDescription("Controlar os grupos de permissões");
		register(new PermissionsGroupCreateCommand());
		register(new PermissionsGroupDeleteCommand());
		register(new PermissionsGroupRemovePermissionCommand());
		register(new PermissionsGroupAddPermissionCommand());
		register(new PermissionsGroupSetPrefixCommand());
		register(new PermissionsGroupSetSuffixCommand());
		register(new PermissionsGroupSetDefaultCommand());
		register(new PermissionsGroupHelpCommand());
		
	}
	

}
