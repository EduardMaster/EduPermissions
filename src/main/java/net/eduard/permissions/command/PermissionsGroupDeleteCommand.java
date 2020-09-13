
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsGroup;
import net.eduard.permissions.manager.PermissionsManager;

public class PermissionsGroupDeleteCommand extends CommandManager {

	public PermissionsGroupDeleteCommand() {
		super("delete", "deletar");
		setUsage("/permissions group delete <group>");
		setDescription("Deletar um grupo de permissões");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		PermissionsManager manager = EduPermissions.getInstance().getManager();
		if (args.length < 3) {
			sendUsage(sender);
		} else {
			String nome = args[2];
			PermissionsGroup group = manager.getGroup(nome);
			if (group != null) {
				manager.removeGroup(group);
				sender.sendMessage(EduPermissions.getInstance().message("group-deleted").replace("$group", "" + nome));
			} else {
				sender.sendMessage(
						EduPermissions.getInstance().message("group-not-exists").replace("$group", "" + nome));
			}
//			
		}

		return true;
	}

}
