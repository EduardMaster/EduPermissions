package net.eduard.permissions

import net.eduard.api.lib.config.Config
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.api.lib.storage.StorageAPI
import net.eduard.api.server.EduardPlugin
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermissionsManager
import net.eduard.permissions.core.PermissionPlayer
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


        storageManager.
        configBases[PermissionPlayer::class.java] =
            Config(this,"players/config.yml")


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

        val it =manager.players.values.iterator()
        while(it.hasNext()){
            val user = it.next()
            if (user.canRemove()){
                manager.players.remove(user.player)
            }else user.save()
        }

    }

    override fun reload() {
        configs.reloadConfig()
        if (configs.contains("permissions")) {
            manager = configs["permissions", PermissionsManager::class.java]
        } else {
            configDefault()
        }
        for (user in storageManager.loadAll(PermissionPlayer::class.java)){
            manager.players[user.player] = user
        }
        StorageAPI.updateReferences()
        PermissionsAPI.instance = manager
        manager.plugin = this

       
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