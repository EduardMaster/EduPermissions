
package net.eduard.permissions;

import org.bukkit.entity.Player;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.game.FakePlayer;
import net.eduard.api.lib.storage.StorageAPI;
import net.eduard.api.server.EduardPlugin;
import net.eduard.permissions.command.PermissionsCommand;
import net.eduard.permissions.event.PermissionEvents;
import net.eduard.permissions.manager.PermissionsGroup;
import net.eduard.permissions.manager.PermissionsManager;
import net.eduard.permissions.manager.PermissionsPlayer;
import net.eduard.permissions.manager.PermissionsPlayerEditor;

public class EduPermissions extends EduardPlugin {
    private static EduPermissions plugin;
    private PermissionsManager manager;

    public static EduPermissions getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;


        reload();
        new PermissionEvents().register(this);
        new PermissionsCommand().register();
    }

    public void save() {
        getConfigs().set("permissions", manager);
        getConfigs().saveConfig();
    }

    public void reload() {
        getConfigs().reloadConfig();
        if (getConfigs().contains("permissions")) {
            manager = getConfigs().get("permissions", (PermissionsManager.class));
            StorageAPI.updateReferences();
        } else {
            configDefault();
        }
        for (Player p : Mine.getPlayers()) {
            PermissionsPlayer conta = manager.getPlayer(p);
            if (conta == null) {
                conta = manager.createPlayer(new FakePlayer(p), "", "", "permissao.exemplo");

            }
            conta.setEditor(new PermissionsPlayerEditor(p, conta));
        }
    }


    public void configDefault() {
        manager = new PermissionsManager();
        PermissionsGroup grupoInicial = manager.createGroup("Padrao", "", "", "permissao.inicial");
        manager.setGroupDefault(grupoInicial);
        save();
    }



    public void onDisable() {
        save();
    }

    public PermissionsManager getManager() {
        return manager;
    }

    public void setManager(PermissionsManager manager) {
        this.manager = manager;
    }

}
