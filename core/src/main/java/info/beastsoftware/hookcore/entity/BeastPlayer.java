package info.beastsoftware.hookcore.entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.UUID;


public interface BeastPlayer extends BeastEntity, AnimalTamer {

    UUID getUuid();

    @Override
    default UUID getUniqueId(){
        return this.getUuid();
    }


    default OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.getUuid());
    }

    default String getName() {
        return this.getOfflinePlayer().getName();
    }

    default Player getBukkitPlayer() {
        return Bukkit.getPlayer(this.getUuid());
    }

    default boolean isOnline() {
        return Objects.nonNull(this.getBukkitPlayer());
    }

    default boolean isOffline() {
        return !this.isOnline();
    }

    default Location getBukkitLocation() {
        if (this.isOnline()) {
            return this.getBukkitPlayer().getLocation();
        }
        return null;
    }

    default BeastLocation getLocation() {
        Location location = this.getBukkitLocation();
        if (Objects.nonNull(location)) {
            return new BeastLocationImpl(location);
        }
        return null;
    }

    default BeastFaction getFactionAtMyLocation() {
        return this.getLocation().getFactionAt();
    }

    default BeastFaction getMyFaction() {
        return this.manager().getFactionOfPlayer(this);
    }

    default boolean isHisFaction(BeastFaction faction) {
        return this.getMyFaction().equals(faction);
    }

    default boolean isAtHisFactionsLand() {
        return this.getFactionAtMyLocation().equals(this.getMyFaction());
    }

    default boolean isEnemy(BeastPlayer player) {
        return this.getMyFaction().isEnemy(player.getMyFaction());
    }

    default boolean isAlly(BeastPlayer player) {
        return this.getMyFaction().isAlly(player.getMyFaction());
    }

    default boolean isNeutral(BeastPlayer player) {
        return this.getMyFaction().isNeutral(player.getMyFaction());
    }

    default BeastRole getRole() {
        return this.getMyFaction().getRoleOfPlayer(this);
    }


    default boolean isAdmin() {
        return this.manager().isAdmin(this);
    }


    default boolean isColeader() {
        return this.manager().isColeader(this);
    }


    default boolean isMod() {
        return this.manager().isMod(this);
    }


    default boolean isRecruit() {
        return this.manager().isRecruit(this);
    }

    default boolean isMember() {
        return !isRecruit() && !isMod() && !isAdmin() && !isColeader() && hasFaction();
    }

    default boolean hasFaction() {
        return this.manager().hasFaction(this);
    }

    default void sms(String message) {
        if (this.isOnline()) {
            this.getBukkitPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    default void openInventory(Inventory inventory) {
        if (this.isOnline()) {
            this.getBukkitPlayer().openInventory(inventory);
        }
    }

    default void performCommand(String command) {
        if (this.isOnline()) {
            this.getBukkitPlayer().performCommand(command);
        }
    }

    default void removePotionEffect(PotionEffectType effect) {
        if (this.isOnline()) {
            this.getBukkitPlayer().removePotionEffect(effect);
        }
    }

    default ItemStack getItemInHand() {
        if (this.isOnline()) {
            return this.getBukkitPlayer().getItemInHand();
        }
        return null;
    }

    default void setItemInHand(ItemStack itemInHand) {
        if (this.isOnline()) {
            this.getBukkitPlayer().setItemInHand(itemInHand);
        }
    }

    default PlayerInventory getInventory() {
        if (this.isOnline()) {
            return this.getBukkitPlayer().getInventory();
        }
        return null;
    }

    default void addPotionEffect(PotionEffect effect) {
        if (this.isOnline()) {
            this.getBukkitPlayer().addPotionEffect(effect);
        }
    }

    default boolean hasPermission(String permission) {
        if (this.isOnline()) {
            return this.getBukkitPlayer().hasPermission(permission);
        }
        return false;
    }

    default boolean isInOthersLand() {
        return !this.isAtHisFactionsLand();
    }

    default boolean isLocationInOthersLand(BeastLocation location) {
        return !this.getMyFaction().equals(location.getFactionAt());
    }


    default boolean thereAreNearbyEnemies(double radius) {
        return this.getBukkitPlayer()
                .getNearbyEntities(radius, radius, radius)
                .stream()
                .filter(e -> e instanceof Player)
                .map(e -> this.playerManager().getPlayer(e.getUniqueId()))
                .anyMatch(p -> p.isEnemy(this));
    }


}
