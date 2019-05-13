
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.permissions.EduPermissions;

public class PermissionsReloadCommand extends CommandManager {

	public PermissionsReloadCommand() {
		super("reload","recarregar");
		setDescription("Recarregar as permissões e grupos");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		EduPermissions.getInstance().reload();
		sender.sendMessage("§aPlugin EduPermissions salvado com sucesso");
	return true;
	}
	

}
