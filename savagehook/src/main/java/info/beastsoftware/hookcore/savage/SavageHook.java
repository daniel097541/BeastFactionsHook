package info.beastsoftware.hookcore.savage;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Relation;
import com.massivecraft.factions.struct.Role;
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
        return this.getRoleOfPlayer(player).equalsIgnoreCase(Role.LEADER.name());
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
