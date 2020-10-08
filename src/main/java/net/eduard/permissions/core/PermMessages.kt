package net.eduard.permissions.core

import net.eduard.api.lib.config.Config

class PermMessages {


    companion object {
        lateinit var messageConfig: Config
        fun message(key :String) : String{
            return messageConfig.message(key)
        }
    }
}