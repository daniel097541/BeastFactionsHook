package info.beastsoftware.hookcore.savage;

import com.massivecraft.factions.*;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import info.beastsoftware.hookcore.FactionsHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;

public class SavageHook implements FactionsHook {


    private Faction getFactionFromId(String id){
        return Factions.getInstance().getFactionById(id);
    }


    @Override
    public String getFactionNameFromId(String id) {
        return getFactionFromId(id).getTag();
    }

    @Override
    public String getIdOfFactionAtLocation(Location bukkitLocation) {
        return Board.getInstance().getIdAt(new FLocation(bukkitLocation));
    }

    @Override
    public String getFactionAtChunk(Chunk bukkitChunk) {
        return Board.getInstance().getIdAt(new FLocation(bukkitChunk.getBlock(0,0,0).getLocation()));
    }

    @Override
    public String getFactionOfPlayer(OfflinePlayer offlinePlayer) {
        return FPlayers.getInstance().getByOfflinePlayer(offlinePlayer).getFactionId();
    }

    @Override
    public Set<OfflinePlayer> getMembersOfFaction(String id) {
        return getFactionFromId(id).getFPlayers()
                .stream()
                .map(fPlayer -> Bukkit.getOfflinePlayer(fPlayer.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Player> getOnlinePlayers() {
        return null;
    }
}
