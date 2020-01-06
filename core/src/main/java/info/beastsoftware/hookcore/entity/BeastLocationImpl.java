package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BeastLocationImpl implements BeastLocation {

    private final String worldName;
    private final double x;
    private final double y;
    private final double z;

    public BeastLocationImpl(Location location) {
        this.worldName = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }


}
