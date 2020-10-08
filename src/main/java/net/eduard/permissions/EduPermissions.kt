package net.eduard.permissions

import net.eduard.api.server.EduardPlugin
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermMessages
import net.eduard.permissions.core.PermissionsManager

class EduPermissions : EduardPlugin() {
    lateinit var manager: PermissionsManager

    override fun onEnable() {
        instance = this
        isFree = true
        super.onEnable()
        PermissionsAPI.plugin = this
        PermMessages.messageConfig = messages
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
        lateinit var instance: EduPermissions
    }
}