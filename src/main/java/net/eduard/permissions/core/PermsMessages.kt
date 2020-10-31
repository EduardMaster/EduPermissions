package net.eduard.permissions.core

import net.eduard.permissions.api.PermissionsAPI

class PermsMessages {
    companion object {
        fun message(key: String): String {
            return PermissionsAPI.instance.plugin
                .message(key)
        }
    }
}