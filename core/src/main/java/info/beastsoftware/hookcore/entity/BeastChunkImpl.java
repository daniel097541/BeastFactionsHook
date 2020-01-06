package info.beastsoftware.hookcore.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Chunk;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class BeastChunkImpl implements BeastChunk {
    private final String worldName;
    private final int x;
    private final int z;

    public BeastChunkImpl(Chunk chunk) {
        this.worldName = chunk.getWorld().getName();
        this.x = chunk.getX();
        this.z = chunk.getZ();
    }
}
