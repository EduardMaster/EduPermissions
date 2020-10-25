package net.eduard.permissions.core

import net.eduard.permissions.api.PermissionsAPI

class PermMessages {


    companion object {

        fun message(key :String) : String{
            return PermissionsAPI.instance.plugin
                .message(key)
        }
    }
}