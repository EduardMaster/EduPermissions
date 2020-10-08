
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.core.PermissionsGroup;
import net.eduard.permissions.core.PermissionsManager;

public class PermissionsGroupSetSuffixCommand extends CommandManager {

	public PermissionsGroupSetSuffixCommand() {
		super("setsuffix", "definirsufixo");
		setUsage("/permissions group setsuffix <group> <suffix>");
		setDescription("Definir o sufixo do grupo");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		PermissionsManager manager = EduPermissions.getInstance().getManager();
		if (args.length < 4) {
			sendUsage(sender);
		} else {
			String nome = args[2];
			String prefixo = Mine.toChatMessage(args[3]);
			PermissionsGroup group = manager.getGroup(nome);
			if (group != null) {
				group.prefix = prefixo;
				sender.sendMessage(EduPermissions.getInstance().message("group-set-suffix").replace("$group", "" + nome)
						.replace("$suffix", prefixo));
			} else {
				sender.sendMessage(
						EduPermissions.getInstance().message("group-not-exists").replace("$group", "" + nome));
			}
//				
		}

		return true;
	}

}
