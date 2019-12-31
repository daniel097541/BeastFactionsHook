package info.beastsoftware.hookcore;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public interface FactionsHook {
    String getFactionNameFromId(String id);

    String getIdOfFactionAtLocation(Location bukkitLocation);

    String getFactionAtChunk(Chunk bukkitChunk);

    String getFactionOfPlayer(OfflinePlayer offlinePlayer);

    Set<OfflinePlayer> getMembersOfFaction(String id);

    Set<Player> getOnlinePlayers();


}
