package info.beastsoftware.hookcore.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


public interface BeastLocation extends BeastEntity {


    String getWorldName();

    double getX();

    double getY();

    double getZ();


    default World getWorld() {
        return Bukkit.getWorld(this.getWorldName());
    }

    default Location getBukkitLocation() {
        return new Location(this.getWorld(), this.getX(), this.getY(), this.getZ());
    }

    default BeastFaction getFactionAt() {
        return this.manager().getFactionAtLocation(this);
    }

    default BeastChunk getChunk() {
        return new BeastChunkImpl(this.getBukkitLocation().getChunk());
    }
}
