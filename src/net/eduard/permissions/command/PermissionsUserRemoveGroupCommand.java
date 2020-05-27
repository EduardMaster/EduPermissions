
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.player.FakePlayer;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsGroup;
import net.eduard.permissions.manager.PermissionsManager;
import net.eduard.permissions.manager.PermissionsPlayer;

public class PermissionsUserRemoveGroupCommand extends CommandManager {

	public PermissionsUserRemoveGroupCommand() {
		super("removegroup", "removergrupo");
		setUsage("/permissions user removegroup <player> <group>");
		setDescription("Remover um grupo para o jogador");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		PermissionsManager manager = EduPermissions.getInstance().getManager();
		if (args.length < 4) {
			sendUsage(sender);
		} else {
			String nome = args[2];
			String grupo = args[3];
			PermissionsPlayer user = manager.getPlayer(new FakePlayer(nome));

			PermissionsGroup group = manager.getGroup(grupo);
			if (group == null) {
				sender.sendMessage(
						EduPermissions.getInstance().message("group-not-exists").replace("$group", "" + grupo));
			} else if (user != null) {
				user.getGroups().remove(group);

				sender.sendMessage(EduPermissions.getInstance().message("player-remove-group")
						.replace("$player", "" + nome).replace("$group", grupo));
			} else {
				sender.sendMessage(
						EduPermissions.getInstance().message("player-not-exists").replace("$player", "" + nome));
			}
//			
		}

		return true;
	}

}
