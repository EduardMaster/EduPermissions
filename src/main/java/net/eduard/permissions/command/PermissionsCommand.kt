package net.eduard.permissions.command

import net.eduard.api.lib.command.Command

class PermissionsCommand : Command("permissions","perm","permission") {
    init {
        register(PermissionsGroupCommand())
        register(PermissionsUserCommand())
        register(PermissionsHelpCommand())
        register(PermissionsSaveCommand())
        register(PermissionsReloadCommand())
    }
}