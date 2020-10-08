package net.eduard.permissions.api

import net.eduard.api.lib.plugin.IPlugin
import net.eduard.permissions.core.PermissionsManager

class PermissionsAPI {

    companion object{
        lateinit var manager : PermissionsManager
        lateinit var plugin : IPlugin
        fun getInstance(): PermissionsManager {

            return manager

        }



    }
}