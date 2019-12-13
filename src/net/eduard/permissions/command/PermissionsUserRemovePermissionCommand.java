
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsManager;
import net.eduard.permissions.manager.PermissionsPlayer;

public class PermissionsUserRemovePermissionCommand extends CommandManager {

	public PermissionsUserRemovePermissionCommand() {
		super("removepermission","removerpermissao");
		setUsage("/permissions user removepermission <player> <permission>");
		setDescription("Remover uma permissão para o jogador");
		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

			PermissionsManager manager = EduPermissions.getInstance().getManager();
			if (args.length < 4) {
				sendUsage(sender);
			} else {
				String nome = args[2];
				String perm = args[3];
				PermissionsPlayer user = manager.getPlayer(new FakePlayer(nome));

//				PermissionsGroup group = manager.getGroup(nome);
				if (user != null) {
					user.getPermissions().remove(perm);
					sender.sendMessage(EduPermissions.getInstance().message("player-remove-permission")
							.replace("$player", "" + nome).replace("$permission", perm));
				} else {
					sender.sendMessage(
							EduPermissions.getInstance().message("player-not-exists").replace("$player", "" + nome));
				}
//				
			}

			return true;
		}

}
