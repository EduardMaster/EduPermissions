
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.permissions.EduPermissions;

public class PermissionsSaveCommand extends CommandManager {

	public PermissionsSaveCommand() {
		super("save","salvar");
		setDescription("Salvar as permissões e grupos");
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	
		EduPermissions.getInstance().save();
		sender.sendMessage("§aPlugin EduPermissions salvado com sucesso");
		return true;
	}
	

}
