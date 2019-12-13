package net.eduard.permissions.bungee;

import net.eduard.api.server.EduardBungeePlugin;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class PermissionBungee extends EduardBungeePlugin implements Listener {
	@Override
	public void onEnable() {
		plugin = this;
		BungeeCord.getInstance().getPluginManager().registerListener(this,
				new PermissionEvents());

		BungeeCord.getInstance().getPluginManager().registerCommand(this,
				new PermissionCommand());
		BungeeCord.getInstance().getConsole()
				.sendMessage(new TextComponent("Plugin ativado"));
	}
	private static PermissionBungee plugin;
	public static PermissionBungee getPlugin() {
		return plugin;
	}
}
