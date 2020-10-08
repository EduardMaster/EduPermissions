package net.eduard.permissions

import net.eduard.api.server.EduardBungeePlugin
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermissionsManager

class EduBungeePermissions : EduardBungeePlugin() {
    lateinit var manager: PermissionsManager

    override fun onEnable() {
        instance = this
        isFree = true
        super.onEnable()
        reload()
        if (configs.getBoolean("enable-commands"))
            PermissionsCommand().register(this)
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
        lateinit var instance: EduardBungeePlugin
    }

}