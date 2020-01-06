package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.entity.BeastPlayer;
import info.beastsoftware.hookcore.manager.PlayerManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public interface PlayerService {


    PlayerManager getManager();


    default BeastPlayer getFromName(String name) {
        return this.getManager().getPlayer(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    default BeastPlayer getFromUUID(UUID uuid) {
        return this.getManager().getPlayer(uuid);
    }

}
