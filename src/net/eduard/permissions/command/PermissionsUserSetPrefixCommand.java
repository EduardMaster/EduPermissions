
package net.eduard.permissions.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsManager;
import net.eduard.permissions.manager.PermissionsPlayer;

public class PermissionsUserSetPrefixCommand extends CommandManager {

	public PermissionsUserSetPrefixCommand() {
		super("setprefix", "definirprefixo");
		setUsage("/permissions user setprefix <player> <permission>");
		setDescription("Definir o prefixo do jogador");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		PermissionsManager manager = EduPermissions.getInstance().getManager();
		if (args.length < 4) {
			sendUsage(sender);
		} else {
			String nome = args[2];
			String prefixo = Mine.toChatMessage(args[3]);
			PermissionsPlayer user = manager.getPlayer(new FakePlayer(nome));

//			PermissionsGroup group = manager.getGroup(nome);
			if (user != null) {
				user.setPrefix(prefixo);
				sender.sendMessage(EduPermissions.getInstance().message("player-set-prefix")
						.replace("$player", "" + nome).replace("$prefix", prefixo));
			} else {
				sender.sendMessage(
						EduPermissions.getInstance().message("player-not-exists").replace("$player", "" + nome));
			}
//			
		}

		return true;
	}

}
