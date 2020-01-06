package info.beastsoftware.hookcore.manager;

import info.beastsoftware.hookcore.entity.BeastPlayer;
import org.bukkit.entity.AnimalTamer;

import java.util.UUID;

public interface PlayerManager {


    BeastPlayer getPlayer(UUID uuid);

    default BeastPlayer getPlayer(AnimalTamer player) {
        return this.getPlayer(player.getUniqueId());
    }

}
