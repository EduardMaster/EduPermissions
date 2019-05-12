package net.eduard.permissions.bungee;

import org.bukkit.event.EventHandler;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;

public class TemplateEvents implements Listener{

	@EventHandler
	public void event(ServerConnectedEvent e) {
		ProxiedPlayer p = e.getPlayer();
		
	}
	@EventHandler
	public void onMessage(PluginMessageEvent e) {
		if (Template.getPlugin().getDescription().getName().equals(e.getTag())) {

			
		}
	
	}
}
