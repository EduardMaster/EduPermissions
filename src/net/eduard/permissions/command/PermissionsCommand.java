
package net.eduard.permissions.command;

import net.eduard.api.lib.manager.CommandManager;

public class PermissionsCommand extends CommandManager {

	public PermissionsCommand() {
		super("permissions");
		
		register(new PermissionsGroupCommand());
		register(new PermissionsUserCommand());
		register(new PermissionsHelpCommand());
		register(new PermissionsSaveCommand());
		register(new PermissionsReloadCommand());
	}
	

}
