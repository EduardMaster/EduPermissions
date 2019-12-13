
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;

public class PermissionsHelpCommand extends CommandManager {

	public PermissionsHelpCommand() {
		super("help", "ajuda", "?");
		setDescription("Ver como usa os comandos");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		for (CommandManager sub : getParent().getCommands().values()) {
			if (sender.hasPermission(sub.getPermission())) {
				sender.sendMessage("§3"+sub.getUsage() + " §b" + sub.getDescription());
			}
		}

		return true;
	}

}
