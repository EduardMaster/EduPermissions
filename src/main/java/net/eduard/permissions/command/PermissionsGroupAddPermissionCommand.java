
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsGroup;
import net.eduard.permissions.manager.PermissionsManager;

public class PermissionsGroupAddPermissionCommand extends CommandManager {

	public PermissionsGroupAddPermissionCommand() {
		super("addpermission", "adicionarpermissao");
		setUsage("/permissions group addpermission <group> <permission>");
		setDescription("Adicionar permissao para o grupo");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		PermissionsManager manager = EduPermissions.getInstance().getManager();
		if (args.length < 4) {
			sendUsage(sender);
		} else {
			String nome = args[2];
			PermissionsGroup group = manager.getGroup(nome);
			String perm = args[3];
			if (group != null) {
				group.getPermissions().add(perm);
				sender.sendMessage(EduPermissions.getInstance().message("group-add-permission")
						.replace("$group", "" + nome).replace("$permission", perm));
			} else {
				sender.sendMessage(
						EduPermissions.getInstance().message("group-not-exists").replace("$group", "" + nome));
			}
//			
		}

		return true;
	}
}
