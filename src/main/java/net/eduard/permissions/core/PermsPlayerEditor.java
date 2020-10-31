package net.eduard.permissions.core;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;

import net.eduard.api.lib.modules.Extra;

/**
 * Esta classe foi feita com base no Lucky Perms
 *
 * @author Eduard
 */
public class PermsPlayerEditor extends PermissibleBase {
    /**
     * All permission checks made on standard Bukkit objects are effectively proxied
     * to a {@link PermissibleBase} object, held as a variable on the object.
     * <p>
     * This field is where the permissible is stored on a HumanEntity.
     */
    private static Field HUMAN_ENTITY_PERMISSIBLE_FIELD;

    static {
        /**
         * The field where attachments are stored on a permissible base.
         */
        Field PERMISSIBLE_BASE_ATTACHMENTS_FIELD;
        try {
            @SuppressWarnings("unused")

            // Try to load the permissible field.
            Field humanEntityPermissibleField;
            try {
                // craftbukkit
                humanEntityPermissibleField = Extra.getClassFrom("#centity.CraftHumanEntity").getDeclaredField("perm");
                humanEntityPermissibleField.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
                // glowstone
                humanEntityPermissibleField = Class.forName("net.glowstone.entity.GlowHumanEntity")
                        .getDeclaredField("permissions");
                humanEntityPermissibleField.setAccessible(true);
            }
            HUMAN_ENTITY_PERMISSIBLE_FIELD = humanEntityPermissibleField;

            // Try to load the attachments field.
            PERMISSIBLE_BASE_ATTACHMENTS_FIELD = PermissibleBase.class.getDeclaredField("attachments");
            PERMISSIBLE_BASE_ATTACHMENTS_FIELD.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            PERMISSIBLE_BASE_ATTACHMENTS_FIELD = null;
            HUMAN_ENTITY_PERMISSIBLE_FIELD = null;
        }
    }

    private PermsPlayer playerAccount;
    private Player player;

    public PermsPlayerEditor(Player player, PermsPlayer playerAccount) {
        super(player);
        setPlayer(player);
        setPlayerAccount(playerAccount);
        try {
            HUMAN_ENTITY_PERMISSIBLE_FIELD.set(player, this);
        } catch (IllegalArgumentException | IllegalAccessException e) {

            e.printStackTrace();
        }
    }

    public boolean hasPermission(Permission permission) {

        return hasPermission(permission.getName());
    }


    @Override
    public boolean hasPermission(String permission) {

        return getPlayerAccount().hasPermission(permission);

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PermsPlayer getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(PermsPlayer playerAccount) {
        this.playerAccount = playerAccount;
    }

}
