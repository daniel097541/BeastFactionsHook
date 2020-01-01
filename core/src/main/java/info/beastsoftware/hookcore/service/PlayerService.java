package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.entity.BeastPlayer;
import info.beastsoftware.hookcore.manager.PlayerManager;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerService {

    private final PlayerManager manager = PlayerManager.getInstance();


    @Getter
    private static final PlayerService instance = new PlayerService();


    public BeastPlayer getFromName(String name){
        return manager.get(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public BeastPlayer getFromUUID(UUID uuid){
        return manager.get(uuid);
    }

}
