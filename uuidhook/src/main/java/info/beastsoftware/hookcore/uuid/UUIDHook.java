package info.beastsoftware.hookcore.uuid;

import info.beastsoftware.hookcore.FactionsHook;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class UUIDHook implements FactionsHook {
    @Override
    public String getFactionNameFromId(String id) {
        return null;
    }

    @Override
    public String getIdOfFactionAtLocation(Location bukkitLocation) {
        return null;
    }

    @Override
    public String getFactionAtChunk(Chunk bukkitChunk) {
        return null;
    }

    @Override
    public String getFactionOfPlayer(OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public Set<OfflinePlayer> getMembersOfFaction(String id) {
        return null;
    }

    @Override
    public Set<Player> getOnlinePlayers() {
        return null;
    }
}
