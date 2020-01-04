package info.beastsoftware.hookcore;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface FactionsHook {
    String getFactionNameFromId(String id);

    String getFactionAtChunk(Chunk bukkitChunk);

    String getFactionOfPlayer(OfflinePlayer offlinePlayer);

    Set<OfflinePlayer> getMembersOfFaction(String id);

    Set<Player> getOnlinePlayers(String factionId);

    String getRelationOfFactionWithFaction(String factionId, String targetFactionId);

    default String getIdOfFactionAtLocation(Location bukkitLocation) {
        return this.getFactionAtChunk(bukkitLocation.getChunk());
    }

    default String getFactionOfPlayer(Player player) {
        return this.getFactionOfPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
    }

    default String getRelationOfPlayerWith(Player player, Player target) {
        return this.getRelationOfFactionWithFaction(this.getFactionOfPlayer(player), this.getFactionOfPlayer(target));
    }

    default boolean areTheyAllies(String faction1, String faction2) {
        return this.getRelationOfFactionWithFaction(faction1, faction2).toLowerCase().contains("ally");
    }

    default boolean isAllyOf(Player player, Player other) {
        return this.areTheyAllies(this.getFactionOfPlayer(player), this.getFactionOfPlayer(other));
    }

    default boolean areTheyEnemies(String faction1, String faction2) {
        return this.getRelationOfFactionWithFaction(faction1, faction2).toLowerCase().contains("enemy");
    }


    default boolean isEnemyOf(Player player, Player other) {
        return this.areTheyEnemies(this.getFactionOfPlayer(player), this.getFactionOfPlayer(other));
    }

    default boolean areTheyNeutrals(String faction1, String faction2) {
        return this.getRelationOfFactionWithFaction(faction1, faction2).toLowerCase().contains("neutral");
    }

    default boolean isNeutralTo(Player player, Player other) {
        return this.areTheyNeutrals(this.getFactionOfPlayer(player), this.getFactionOfPlayer(other));
    }

    default boolean areInTheSameFaction(Player player1, Player player2) {
        return this.getFactionOfPlayer(player1).equalsIgnoreCase(this.getFactionOfPlayer(player2));
    }

    default Set<Player> getNearbyPlayersInRadiusMatching(Player player, double radius, Function<Player, Boolean> function) {
        return player.getNearbyEntities(radius, radius, radius)
                .stream()
                .filter(e -> e instanceof Player)
                .map(e -> (Player) e)
                .filter(function::apply)
                .collect(Collectors.toSet());
    }

    default Set<Player> getNearbyEnemiesInRadius(Player player, double radius) {
        return this.getNearbyPlayersInRadiusMatching(player, radius, (p) -> isEnemyOf(player, p));
    }

    default Set<Player> getNearbyNeutralsInRadius(Player player, double radius) {
        return this.getNearbyPlayersInRadiusMatching(player, radius, (p) -> isNeutralTo(player, p));
    }

    default Set<Player> getNearbyOutsidersInRadius(Player player, double radius) {
        return this.getNearbyPlayersInRadiusMatching(player, radius, (p) -> !areInTheSameFaction(player, p));
    }

    String getFactionIdFromName(String factionName);

    String getRoleOfPlayer(OfflinePlayer offlinePlayer);

    boolean isAdmin(OfflinePlayer player);

    boolean isColeader(OfflinePlayer player);

    boolean isMod(OfflinePlayer player);

    boolean isRecruit(OfflinePlayer player);

    boolean hasFaction(OfflinePlayer offlinePlayer);
}
