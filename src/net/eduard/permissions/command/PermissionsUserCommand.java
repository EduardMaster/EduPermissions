
package net.eduard.permissions.command;

import net.eduard.api.lib.manager.CommandManager;

public class PermissionsUserCommand extends CommandManager {

	public PermissionsUserCommand() {
		super("user", "usuario");
		setDescription("Controlar as permissões e grupos dos jogadores");
		register(new PermissionsUserAddGroupCommand());
		register(new PermissionsUserRemoveGroupCommand());
		register(new PermissionsUserAddPermissionCommand());
		register(new PermissionsUserRemovePermissionCommand());
		register(new PermissionsUserHelpCommand());
		register(new PermissionsUserSetPrefixCommand());
		register(new PermissionsUserSetSuffixCommand());

	}

}
