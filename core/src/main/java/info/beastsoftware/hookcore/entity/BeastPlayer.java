package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class BeastPlayer implements BeastEntity{

    private final UUID uuid;

    public BeastPlayer(Player player) {
        this.uuid = player.getUniqueId();
    }

    public BeastPlayer(OfflinePlayer offlinePlayer) {
        this.uuid = offlinePlayer.getUniqueId();
    }

    public OfflinePlayer getOfflinePlayer(){
        return Bukkit.getOfflinePlayer(this.uuid);
    }

    public String getName(){
        return this.getOfflinePlayer().getName();
    }

    public Player getBukkitPlayer(){
        return Bukkit.getPlayer(this.uuid);
    }

    public boolean isOnline(){
        return Objects.nonNull(this.getBukkitPlayer());
    }

    public boolean isOffline(){
        return !this.isOnline();
    }

    public Location getBukkitLocation(){
        if(this.isOnline()){
            return this.getBukkitPlayer().getLocation();
        }
        return null;
    }

    public BeastLocation getLocation(){
        Location location = this.getBukkitLocation();
        if(Objects.nonNull(location)) {
            return new BeastLocation(location);
        }
        return null;
    }

    public BeastFaction getFactionAtMyLocation(){
        return this.getLocation().getFactionAt();
    }

    public BeastFaction getMyFaction(){
        return this.manager().getFactionOfPlayer(this);
    }
}
