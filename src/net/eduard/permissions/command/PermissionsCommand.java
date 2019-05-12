
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;

public class PermissionsCommand extends CommandManager {

	public PermissionsCommand() {
		super("permissions");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
		String[] args) {
	
		return true;
	}

}
