package net.eduard.permissions

import net.eduard.api.lib.modules.VaultAPI
import net.eduard.api.server.EduardPlugin
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermMessages
import net.eduard.permissions.core.PermissionsManager
import net.eduard.permissions.core.PermissionsVaultSupport
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority

class EduPermissions : EduardPlugin() {
    lateinit var manager: PermissionsManager

    override fun onEnable() {
        instance = this
        isFree = true
        super.onEnable()
        PermissionsAPI.plugin = this
        PermMessages.messageConfig = messages
        reload()
        configs.add("enable-commands" ,true)
        configs.saveConfig()
        if (configs.getBoolean("enable-commands"))
            PermissionsCommand().register(this)

        Bukkit.getServicesManager().register(
            Permission::class.java
        , PermissionsVaultSupport(),this , ServicePriority.Normal)
        VaultAPI.setupVault()

    }

    override fun save() {
        configs["permissions"] = manager
        configs.saveConfig()
    }

    override fun reload() {
        storage.reloadConfig()
        if (configs.contains("permissions")) {
            manager = configs["permissions", PermissionsManager::class.java]
        } else {
            configDefault()
        }
        PermissionsAPI.manager = manager
    }

    override fun configDefault() {
        manager = PermissionsManager()
        manager.setDefaultGroup(
            manager.createGroup("Padrao", "", "", "permissao.inicial")
        )

        save()
    }

    override fun onDisable() {
        save()
        super.onDisable()
    }

    companion object {
        lateinit var instance: EduPermissions
    }
}