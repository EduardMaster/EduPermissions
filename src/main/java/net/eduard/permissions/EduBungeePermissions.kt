package net.eduard.permissions

import net.eduard.api.server.EduardBungeePlugin
import net.eduard.permissions.api.PermissionsAPI
import net.eduard.permissions.command.PermissionsCommand
import net.eduard.permissions.core.PermMessages
import net.eduard.permissions.core.PermissionsManager
import net.eduard.permissions.core.PermissionsPlayer
import net.eduard.permissions.listener.PermissionsBungeeListener
import net.md_5.bungee.api.ProxyServer

class EduBungeePermissions : EduardBungeePlugin() {
    lateinit var manager: PermissionsManager

    override fun onEnable() {
        instance = this
        isFree = true
        super.onEnable()
        PermissionsAPI.plugin = this


        reload()
        config.add("enable-commands",true)
        config.saveConfig()
        if (configs.getBoolean("enable-commands"))
            PermissionsCommand().register(this)


       ProxyServer.getInstance().pluginManager.
       registerListener(this,PermissionsBungeeListener())
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
        storage.reloadConfig()
        if (configs.contains("permissions")) {
            manager = configs["permissions", PermissionsManager::class.java]
        } else {
            configDefault()
        }
        for (user in storageManager.loadAll(PermissionsPlayer::class.java)){
            manager.players[user.player] = user
        }
        for (user in manager.players.values){
            for (groupName in user.groupsNames){
                val group = manager.getGroup(groupName)?:continue
                user.groups.add(group)
            }
        }
        PermissionsAPI.manager = manager
    }

    override fun configDefault() {
        manager = PermissionsManager()
        manager.setDefaultGroup(
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