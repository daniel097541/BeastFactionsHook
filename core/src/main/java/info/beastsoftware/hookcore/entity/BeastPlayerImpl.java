package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.entity.AnimalTamer;

import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class BeastPlayerImpl implements BeastPlayer {

    private final UUID uuid;

    public BeastPlayerImpl(AnimalTamer player) {
        this.uuid = player.getUniqueId();
    }
}
