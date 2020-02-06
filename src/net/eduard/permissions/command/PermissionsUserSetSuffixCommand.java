
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsManager;
import net.eduard.permissions.manager.PermissionsPlayer;

public class PermissionsUserSetSuffixCommand extends CommandManager {

	public PermissionsUserSetSuffixCommand() {
		super("setsuffix", "definirsufixo");
		setUsage("/permissions user setsuffix <player> <permission>");
		setDescription("Definir o sufixo do jogador");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		PermissionsManager manager = EduPermissions.getInstance().getManager();
		if (args.length < 4) {
			sendUsage(sender);
		} else {
			String nome = args[2];
			String suffixo = Mine.toChatMessage(args[3]);
			PermissionsPlayer user = manager.getPlayer(new FakePlayer(nome));

//			PermissionsGroup group = manager.getGroup(nome);
			if (user != null) {
				user.setSuffix(suffixo);
				sender.sendMessage(EduPermissions.getInstance().message("player-set-prefix")
						.replace("$player", "" + nome).replace("$prefix", suffixo));
			} else {
				sender.sendMessage(
						EduPermissions.getInstance().message("player-not-exists").replace("$player", "" + nome));
			}
//			
		}

		return true;
	}

}
