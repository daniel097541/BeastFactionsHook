package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BeastChunk implements BeastEntity{

    private final String worldName;
    private final int x;
    private final int z;


    public BeastChunk(Chunk chunk) {
        this.worldName = chunk.getWorld().getName();
        this.x = chunk.getX();
        this.z = chunk.getZ();
    }

    public World getWorld(){
        return Bukkit.getWorld(this.getWorldName());
    }

    public Chunk getBukkitChunk(){
        return this.getWorld().getChunkAt(this.x, this.z);
    }

    public BeastFaction getFaction(){
        return manager().getFactionAtChunk(this);
    }

}
