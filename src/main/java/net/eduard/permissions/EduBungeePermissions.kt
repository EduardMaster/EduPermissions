package net.eduard.permissions

import net.eduard.api.server.EduardBungeePlugin
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermsManager
import net.eduard.permissions.listener.PermissionsBungeeListener
import net.md_5.bungee.api.ProxyServer

class EduBungeePermissions : EduardBungeePlugin() {
    lateinit var manager: PermsManager

    override fun onEnable() {
        instance = this

        super.onEnable()
        manager = PermsManager()
        PermissionsAPI.instance = manager
        manager.plugin = this

        reload()
        config.add("enable-commands",true)
        config.saveConfig()
        if (configs.getBoolean("enable-commands"))
            PermissionsCommand().register(this)


       ProxyServer.getInstance().pluginManager.
       registerListener(this,PermissionsBungeeListener())
    }

    override fun save() {

    }

    override fun reload() {
        PermissionsAPI.instance.reload()
    }

    override fun configDefault() {
        manager.groupDefault =(
            manager.createGroup("Padrao", "", "",
                "permissao.inicial")
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