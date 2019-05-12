
package net.eduard.permissions.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.eduard.api.lib.manager.EventsManager;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.permissions.EduPermissions;
import net.eduard.permissions.manager.PermissionsPlayer;
import net.eduard.permissions.manager.PermissionsPlayerEditor;

public class PermissionEvents extends EventsManager {

	@EventHandler
	public void event(PlayerMoveEvent e) {

	}
	@EventHandler
	public void event(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PermissionsPlayer conta = EduPermissions.getInstance().getManager().getPlayer(p);
		if (conta == null) {
			conta = EduPermissions.getInstance().getManager().createPlayer(new FakePlayer(p),"","","permissao.exemplo");
			
		}
		conta.setEditor(new PermissionsPlayerEditor(p,conta));
		p.setOp(false);
		if (p.hasPermission("beta.punheta.cu")) {
			p.sendMessage("§aSim o beta é viado");
		}else {
			p.sendMessage("§cNão o beta é viado sim");
		}
	}

}
