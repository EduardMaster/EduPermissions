package net.eduard.permissions.bungee;



import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PermissionEvents implements Listener{

	@EventHandler
	public void event(ServerConnectedEvent e) {
		ProxiedPlayer p = e.getPlayer();
		
	}
	@EventHandler
	public void onMessage(PluginMessageEvent e) {
		if (PermissionBungee.getInstance().getDescription().getName().equals(e.getTag())) {

			
		}
	
	}
}
