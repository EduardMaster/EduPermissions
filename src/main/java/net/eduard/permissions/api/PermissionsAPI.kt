package net.eduard.permissions.api

import net.eduard.permissions.EduPermissions
import net.eduard.permissions.core.PermissionsManager

class PermissionsAPI {

    companion object{
        fun getInstance(): PermissionsManager {
            return EduPermissions.instance.manager
        }

    }
}