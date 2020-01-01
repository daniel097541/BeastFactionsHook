package info.beastsoftware.mcorehook;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayerColl;
import com.massivecraft.massivecore.ps.PS;
import info.beastsoftware.hookcore.FactionsHook;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface MCoreFactionsHook extends FactionsHook {

    default Faction fromId(String id) {
        Map<String, Faction> factions = FactionColl.get().getIdToEntity();
        return factions.get(id);
    }

    @Override
    default Set<Player> getOnlinePlayers(String factionId) {
        return new HashSet<>(this.fromId(factionId).getOnlinePlayers());
    }

    @Override
    default String getRelationOfFactionWithFaction(String factionId, String targetFactionId) {
        return this.fromId(factionId).getRelationWish(this.fromId(targetFactionId)).getName();
    }

    @Override
    default String getFactionNameFromId(String id) {
        return this.fromId(id).getName();
    }

    @Override
    default String getFactionAtChunk(Chunk bukkitChunk) {
        return BoardColl.get().getFactionAt(PS.valueOf(bukkitChunk.getBlock(0, 0, 0).getLocation())).getId();
    }

    @Override
    default String getFactionOfPlayer(OfflinePlayer offlinePlayer) {
        return MPlayerColl.get().get(offlinePlayer).getFaction().getId();
    }

    @Override
    default Set<OfflinePlayer> getMembersOfFaction(String id) {
        return this.fromId(id)
                .getMPlayers()
                .stream()
                .map(p -> Bukkit.getOfflinePlayer(p.getUuid()))
                .collect(Collectors.toSet());
    }
}
