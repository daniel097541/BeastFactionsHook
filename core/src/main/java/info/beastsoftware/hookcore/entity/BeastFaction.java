package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public interface BeastFaction extends BeastEntity {
    String getId();

    default String getName() {
        return this.manager().getFactionName(this);
    }

    default Set<BeastPlayer> getOnlinePlayers(){
        return this.manager().getOnlinePlayersOfFaction(this);
    }

    default Set<BeastPlayer> getMembers() {
        return this.manager().getMembersOfFaction(this);
    }

    default BeastRelation getRelationWith(BeastFaction otherFaction) {
        return this.manager().getRelationOfFactionWith(this, otherFaction);
    }

    default boolean isEnemy(BeastFaction other) {
        return this.getRelationWith(other).getName().toLowerCase().contains("enemy");
    }

    default boolean isAlly(BeastFaction other) {
        return this.getRelationWith(other).getName().toLowerCase().contains("ally");
    }

    default boolean isNeutral(BeastFaction other) {
        return this.getRelationWith(other).getName().contains("neutral");
    }

    default BeastRole getRoleOfPlayer(BeastPlayer player) {
        return this.manager().getRoleOfPlayer(player);
    }

    default void broadcastMessageToAllPlayers(String message){
        this.getOnlinePlayers()
                .forEach(p -> p.sms(message));
    }

    default void broadcastMessageToAllPlayersWithRole(String message, BeastRole role){
        this.getOnlinePlayers()
                .stream()
                .filter(player -> player.getRole().equals(role))
                .forEach(p -> p.sms(message));
    }

    default boolean isWilderness(){
        return ChatColor.stripColor(this.getName()).equalsIgnoreCase("wilderness");
    }

    default boolean isWarzone(){
        return  ChatColor.stripColor(this.getName()).equalsIgnoreCase("warzone");
    }

    default boolean isSafezone(){
        return  ChatColor.stripColor(this.getName()).equalsIgnoreCase("safezone");
    }

    default boolean isSystemFaction(){
        return this.isSafezone()
                || this.isWarzone()
                ||this.isWilderness();
    }

    default List<BeastChunk> getChunksClaimedAroundLocation(BeastLocation location, int radius){
        List<BeastChunk> chunks = new ArrayList<>();

        if (radius <= 0) {
            if(location.getFactionAt().equals(this)) {
                chunks.add(location.getChunk());
            }
            return chunks;
        }


        BeastChunk locChunk = location.getChunk();
        chunks.add(locChunk);

        int finalX = radius + locChunk.getX();
        int finalZ = radius + locChunk.getZ();


        int finalXNegative;
        int finalZNegative;

        finalXNegative = locChunk.getX() - radius;
        finalZNegative = locChunk.getZ() - radius;

        //get chunks in a positive radius
        for (int x = locChunk.getX(); x <= finalX; x++) {
            for (int z = locChunk.getZ(); z <= finalZ; z++) {

                BeastChunk chunk = new BeastChunkImpl(location.getWorld().getChunkAt(x, z));

                if(chunk.getFaction().equals(this)){
                    chunks.add(chunk);
                }
            }
        }

        //get chunks in a negative radius
        for (int x = finalXNegative; x <= locChunk.getX(); x++) {
            for (int z = finalZNegative; z <= locChunk.getZ(); z++) {
                BeastChunk chunk = new BeastChunkImpl(location.getWorld().getChunkAt(x, z));

                if(chunk.getFaction().equals(this)){
                    chunks.add(chunk);
                }            }
        }


        return chunks;
    }
}
