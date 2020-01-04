package info.beastsoftware.mcorehook;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
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

    @Override
    default String getFactionIdFromName(String factionName){
        return FactionColl.get().getByName(factionName).getId();
    }

    @Override
    default String getRoleOfPlayer(OfflinePlayer offlinePlayer){
        return MPlayerColl.get().get(offlinePlayer).getRole().getName();
    }

    @Override
    default boolean isAdmin(OfflinePlayer player){
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Rel.LEADER.name());
    }


    @Override
    default boolean isMod(OfflinePlayer player){
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Rel.OFFICER.name());

    }

    @Override
    default boolean isRecruit(OfflinePlayer player){
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Rel.RECRUIT.name());
    }

    @Override
    default boolean hasFaction(OfflinePlayer offlinePlayer){
        return MPlayerColl.get().get(offlinePlayer).hasFaction();
    }

    @Override
    default boolean isColeader(OfflinePlayer player){
        throw new UnsupportedOperationException();
    }

    @Override
    default Set<String> getAllFactions(){
        return FactionColl.get().getAll()
                .stream()
                .map(Faction::getId)
                .collect(Collectors.toSet());
    }
}
