package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BeastLocation implements BeastEntity{

    private final String worldName;
    private final double x;
    private final double y;
    private final double z;

    public BeastLocation(Location location) {
        this.worldName = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

    public World getWorld(){
        return Bukkit.getWorld(this.worldName);
    }

    public Location getBukkitLocation(){
        return new Location(this.getWorld(), this.x, this.y, this.z);
    }

    public BeastFaction getFactionAt(){
        return this.manager().getFactionAtLocation(this);
    }

    public BeastChunk getChunk() {
        return new BeastChunk(this.getBukkitLocation().getChunk());
    }
}
