package net.eduard.permissions.core


class PermissionsGroup {
    var name="Grupo"
    var childrensNames = mutableListOf<String>()
    @Transient
    var childrens = mutableListOf<PermissionsGroup>()
    var permissions = mutableListOf<String>()
    var prefix = ""
    var suffix = ""


}