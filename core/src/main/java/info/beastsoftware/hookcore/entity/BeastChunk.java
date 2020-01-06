package info.beastsoftware.hookcore.entity;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;


public interface BeastChunk extends BeastEntity {

    String getWorldName();

    int getX();

    int getZ();


    default World getWorld() {
        return Bukkit.getWorld(this.getWorldName());
    }

    default Chunk getBukkitChunk() {
        return this.getWorld().getChunkAt(this.getX(), this.getZ());
    }

    default BeastFaction getFaction() {
        return manager().getFactionAtChunk(this);
    }

}
