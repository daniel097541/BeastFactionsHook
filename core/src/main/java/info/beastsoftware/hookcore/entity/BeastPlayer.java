package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class BeastPlayer implements BeastEntity {

    private final UUID uuid;

    public BeastPlayer(Player player) {
        this.uuid = player.getUniqueId();
    }

    public BeastPlayer(OfflinePlayer offlinePlayer) {
        this.uuid = offlinePlayer.getUniqueId();
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.uuid);
    }

    public String getName() {
        return this.getOfflinePlayer().getName();
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public boolean isOnline() {
        return Objects.nonNull(this.getBukkitPlayer());
    }

    public boolean isOffline() {
        return !this.isOnline();
    }

    public Location getBukkitLocation() {
        if (this.isOnline()) {
            return this.getBukkitPlayer().getLocation();
        }
        return null;
    }

    public BeastLocation getLocation() {
        Location location = this.getBukkitLocation();
        if (Objects.nonNull(location)) {
            return new BeastLocation(location);
        }
        return null;
    }

    public BeastFaction getFactionAtMyLocation() {
        return this.getLocation().getFactionAt();
    }

    public BeastFaction getMyFaction() {
        return this.manager().getFactionOfPlayer(this);
    }

    public boolean isHisFaction(BeastFaction faction) {
        return this.getMyFaction().equals(faction);
    }

    public boolean isAtHisFactionsLand() {
        return this.getFactionAtMyLocation().equals(this.getMyFaction());
    }

    public boolean isEnemy(BeastPlayer player) {
        return this.getMyFaction().isEnemy(player.getMyFaction());
    }

    public boolean isAlly(BeastPlayer player) {
        return this.getMyFaction().isAlly(player.getMyFaction());
    }

    public boolean isNeutral(BeastPlayer player) {
        return this.getMyFaction().isNeutral(player.getMyFaction());
    }

    public BeastRole getRole(){
        return this.getMyFaction().getRoleOfPlayer(this);
    }


    public boolean isAdmin(){
        return this.manager().isAdmin(this);
    }


    public boolean isColeader(){
        return this.manager().isColeader(this);
    }


    public boolean isMod(){
        return this.manager().isMod(this);
    }


    public boolean isRecruit(){
        return this.manager().isRecruit(this);
    }

    public boolean isMember(){
        return !isRecruit() && !isMod() && !isAdmin() && !isColeader() && hasFaction();
    }

    public boolean hasFaction() {
        return this.manager().hasFaction(this);
    }

    public void sms(String message){
        if(this.isOnline()){
            this.getBukkitPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public void openInventory(Inventory inventory){
        if(this.isOnline()){
            this.getBukkitPlayer().openInventory(inventory);
        }
    }

    public void performCommand(String command){
        if(this.isOnline()){
            this.getBukkitPlayer().performCommand(command);
        }
    }

    public void removePotionEffect(PotionEffectType effect){
        if(this.isOnline()){
            this.getBukkitPlayer().removePotionEffect(effect);
        }
    }

    public ItemStack getItemInHand(){
        if(this.isOnline()){
            return this.getBukkitPlayer().getItemInHand();
        }
        return null;
    }

    public void setItemInHand(ItemStack itemInHand){
        if(this.isOnline()){
            this.getBukkitPlayer().setItemInHand(itemInHand);
        }
    }

    public PlayerInventory getInventory(){
        if(this.isOnline()){
            return this.getBukkitPlayer().getInventory();
        }
        return null;
    }

    public void addPotionEffect(PotionEffect effect){
        if(this.isOnline()){
            this.getBukkitPlayer().addPotionEffect(effect);
        }
    }

    public boolean hasPermission(String permission){
        if(this.isOnline()){
            return this.getBukkitPlayer().hasPermission(permission);
        }
        return false;
    }

    public boolean isInOthersLand(){
        return !this.isAtHisFactionsLand();
    }

    public boolean isLocationInOthersLand(BeastLocation location){
        return !this.getMyFaction().equals(location.getFactionAt());
    }


    public boolean thereAreNearbyEnemies(double radius){
        return this.getBukkitPlayer()
                .getNearbyEntities(radius, radius, radius)
                .stream()
                .filter(e -> e instanceof Player)
                .map(e -> this.playerManager().get(e.getUniqueId()))
                .anyMatch(p -> p.isEnemy(this));
    }



}
