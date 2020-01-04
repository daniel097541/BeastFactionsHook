package info.beastsoftware.hookcore.uuid;

import com.massivecraft.factions.*;
import com.massivecraft.factions.perms.Role;
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

    @Override
    default String getFactionIdFromName(String factionName){
        return Factions.getInstance().getByTag(factionName).getId();
    }

    @Override
    default String getRoleOfPlayer(OfflinePlayer offlinePlayer){
        return FPlayers.getInstance().getByOfflinePlayer(offlinePlayer).getRole().name();
    }

    @Override
    default boolean isAdmin(OfflinePlayer player){
        return FPlayers.getInstance().getByOfflinePlayer(player).getRole().equals(Role.ADMIN);
    }

    @Override
    default boolean isColeader(OfflinePlayer player){
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Role.COLEADER.name());
    }

    @Override
    default boolean isMod(OfflinePlayer player){
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Role.MODERATOR.name());
    }

    @Override
    default boolean isRecruit(OfflinePlayer player){
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Role.RECRUIT.name());
    }

    @Override
    default boolean hasFaction(OfflinePlayer offlinePlayer){
        return FPlayers.getInstance().getByOfflinePlayer(offlinePlayer).hasFaction();
    }

    @Override
    default Set<String> getAllFactions(){
        return Factions.getInstance().getAllFactions()
                .stream()
                .map(Faction::getId)
                .collect(Collectors.toSet());
    }
}
