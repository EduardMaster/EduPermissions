package net.eduard.permissions.command

import net.eduard.api.lib.command.Command


class PermissionsUserCommand : Command("user", "usuario") {
    init {
        description = "Controlar as permissões e grupos dos jogadores"
        register(PermissionsUserAddGroupCommand())
        register(PermissionsUserRemoveGroupCommand())
        register(PermissionsUserAddPermissionCommand())
        register(PermissionsUserRemovePermissionCommand())
        register(PermissionsUserHelpCommand())
        register(PermissionsUserSetPrefixCommand())
        register(PermissionsUserSetSuffixCommand())
    }
}