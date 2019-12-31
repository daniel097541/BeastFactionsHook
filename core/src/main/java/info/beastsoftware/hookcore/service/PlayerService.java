package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.entity.BeastPlayer;
import info.beastsoftware.hookcore.manager.PlayerManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerService {

    private final PlayerManager manager = PlayerManager.getInstance();



    public BeastPlayer getFromName(String name){
        return manager.get(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public BeastPlayer getFromUUID(UUID uuid){
        return manager.get(uuid);
    }

}
