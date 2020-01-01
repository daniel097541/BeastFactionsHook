package info.beastsoftware.hookcore.uuid;

import com.massivecraft.factions.*;
import info.beastsoftware.hookcore.FactionsHook;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface UUIDHook extends FactionsHook {

    default Faction getFromId(String id){
        return Factions.getInstance().getFactionById(id);
    }


    @Override
    default String getRelationOfFactionWithFaction(String factionId, String targetFactionId){
        return this.getFromId(factionId).getRelationWish(this.getFromId(targetFactionId)).name();
    }

    @Override
    default String getFactionNameFromId(String id) {
        return this.getFromId(id).getTag();
    }

    @Override
    default String getFactionAtChunk(Chunk bukkitChunk) {
        return Board.getInstance().getIdAt(new FLocation(bukkitChunk.getBlock(0,0,0).getLocation()));
    }

    @Override
    default String getFactionOfPlayer(OfflinePlayer offlinePlayer) {
        return FPlayers.getInstance().getByOfflinePlayer(offlinePlayer).getFactionId();
    }

    @Override
    default Set<OfflinePlayer> getMembersOfFaction(String id) {
        return this.getFromId(id)
                .getFPlayers()
                .stream()
                .map(p -> Bukkit.getOfflinePlayer(p.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    default Set<Player> getOnlinePlayers(String factionId) {
        return new HashSet<>(this.getFromId(factionId)
                .getOnlinePlayers());
    }
}
