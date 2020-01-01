package info.beastsoftware.hookcore.savage;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Relation;
import info.beastsoftware.hookcore.FactionsHook;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface SavageHook extends FactionsHook {


    default Faction getFactionFromId(String id) {
        return Factions.getInstance().getFactionById(id);
    }


    @Override
    default String getFactionNameFromId(String id) {
        return getFactionFromId(id).getTag();
    }

    @Override
    default String getFactionAtChunk(Chunk bukkitChunk) {
        return Board.getInstance().getIdAt(new FLocation(bukkitChunk.getBlock(0, 0, 0).getLocation()));
    }

    @Override
    default String getFactionOfPlayer(OfflinePlayer offlinePlayer) {
        return FPlayers.getInstance().getByOfflinePlayer(offlinePlayer).getFactionId();
    }

    @Override
    default Set<OfflinePlayer> getMembersOfFaction(String id) {
        return getFactionFromId(id).getFPlayers()
                .stream()
                .map(fPlayer -> Bukkit.getOfflinePlayer(fPlayer.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    default Set<Player> getOnlinePlayers(String factionId) {
        return new HashSet<>(this.getFactionFromId(factionId).getOnlinePlayers());
    }

    @Override
    default String getRelationOfFactionWithFaction(String factionId, String targetFactionId) {
        Relation relation = this.getFactionFromId(factionId).getRelationTo(this.getFactionFromId(targetFactionId));
        return relation.name();
    }
}
