package info.beastsoftware.hookcore.api;

import info.beastsoftware.hookcore.entity.BeastFaction;
import info.beastsoftware.hookcore.entity.BeastPlayer;
import info.beastsoftware.hookcore.service.FactionServiceImpl;
import info.beastsoftware.hookcore.service.FactionsService;
import info.beastsoftware.hookcore.service.PlayerService;
import info.beastsoftware.hookcore.service.PlayerServiceImpl;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface BeastFactionsHookAPI {

    FactionsService factionsService = FactionServiceImpl.getInstance();
    PlayerService playerService = PlayerServiceImpl.getInstance();

    static BeastFaction getFactionFromId(String id) {
        return factionsService.getFromId(id);
    }

    static BeastFaction getFactionFromName(String factionName) {
        return factionsService.getFromName(factionName);
    }

    static BeastFaction getFactionAtLocation(Location location) {
        return factionsService.getAtLocation(location);
    }

    static BeastFaction getFactionAtChunk(Chunk chunk) {
        return factionsService.getAtLocation(chunk.getBlock(0, 0, 0).getLocation());
    }

    static BeastPlayer getPlayerByUUID(UUID uuid) {
        return playerService.getFromUUID(uuid);
    }

    static BeastPlayer getByPlayer(Player player) {
        return getPlayerByUUID(player.getUniqueId());
    }

    static BeastPlayer getFromName(String name) {
        return getPlayerByUUID(Bukkit.getOfflinePlayer(name).getUniqueId());
    }


    static boolean isHooked(){
        return factionsService.isHooked();
    }

}
