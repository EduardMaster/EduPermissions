package net.eduard.permissions

import net.eduard.api.lib.modules.VaultAPI
import net.eduard.api.server.EduardPlugin
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermsGroup
import net.eduard.permissions.core.PermsManager
import net.eduard.permissions.core.PermsVaultSupport
import net.eduard.permissions.listener.PermissionsListener
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority

class EduPermissions : EduardPlugin() {
    lateinit var manager: PermsManager

    override fun onEnable() {
        instance = this
        isFree = true
        super.onEnable()
        manager = PermsManager()
        PermissionsAPI.instance = manager
        manager.plugin = this



        configs.add("enable-commands", true)
        configs.saveConfig()
        if (configs.getBoolean("enable-commands"))
            PermissionsCommand().register(this)
        reload()
        Bukkit.getServicesManager().register(
            Permission::class.java
            , PermsVaultSupport(), this, ServicePriority.Normal
        )
        VaultAPI.setupVault()
        PermissionsListener().register(this)

    }

    override fun save() {


    }

    override fun reload() {
        manager.reload()

        if (manager.groups.isEmpty()){
          manager.groupDefault=  manager.createGroup("Padrao", "", "", "permissao.inicial")
        }
        if (configs.contains("default-group")) {
            manager.groupDefault = manager.getGroup(configs.getString("default-group"))
        }
        configs.add("default-group", manager.groups.values.first().name)
        configs.saveConfig()
    }

    override fun configDefault() {


    }

    override fun onDisable() {
        save()
        super.onDisable()
    }

    companion object {
        lateinit var instance: EduPermissions
    }
}