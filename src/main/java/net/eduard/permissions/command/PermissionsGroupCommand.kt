package net.eduard.permissions.command

import net.eduard.api.lib.command.Command

class PermissionsGroupCommand : Command("group", "grupo") {
    init {
        description = "Controlar os grupos de permissões"
        register(PermissionsGroupCreateCommand())
        register(PermissionsGroupDeleteCommand())
        register(PermissionsGroupRemovePermissionCommand())
        register(PermissionsGroupAddPermissionCommand())
        register(PermissionsGroupSetPrefixCommand())
        register(PermissionsGroupSetSuffixCommand())
        register(PermissionsGroupSetDefaultCommand())
        register(PermissionsGroupHelpCommand())
    }
}