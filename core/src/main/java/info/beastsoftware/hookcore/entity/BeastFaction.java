package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BeastFaction implements BeastEntity {
    private final String id;

    public String getName() {
        return this.manager().getFactionName(this);
    }

    public Set<BeastPlayer> getOnlinePlayers(){
        return this.manager().getOnlinePlayersOfFaction(this);
    }

    public Set<BeastPlayer> getMembers() {
        return this.manager().getMembersOfFaction(this);
    }

    public BeastRelation getRelationWith(BeastFaction otherFaction) {
        return this.manager().getRelationOfFactionWith(this, otherFaction);
    }

    public boolean isEnemy(BeastFaction other) {
        return this.getRelationWith(other).getName().toLowerCase().contains("enemy");
    }

    public boolean isAlly(BeastFaction other) {
        return this.getRelationWith(other).getName().toLowerCase().contains("ally");
    }

    public boolean isNeutral(BeastFaction other) {
        return this.getRelationWith(other).getName().contains("neutral");
    }

    public BeastRole getRoleOfPlayer(BeastPlayer player) {
        return this.manager().getRoleOfPlayer(player);
    }

    public void broadcastMessageToAllPlayers(String message){
        this.getOnlinePlayers()
                .forEach(p -> p.sms(message));
    }

    public void broadcastMessageToAllPlayersWithRole(String message, BeastRole role){
        this.getOnlinePlayers()
                .stream()
                .filter(player -> player.getRole().equals(role))
                .forEach(p -> p.sms(message));
    }

    public boolean isWilderness(){
        return this.getName().equalsIgnoreCase("wilderness");
    }

    public boolean isWarzone(){
        return this.getName().equalsIgnoreCase("warzone");
    }

    public boolean isSafezone(){
        return this.getName().equalsIgnoreCase("safezone");
    }

    public boolean isSystemFaction(){
        return this.isSafezone()
                || this.isWarzone()
                ||this.isWilderness();
    }

    public List<BeastChunk> getChunksClaimedAroundLocation(BeastLocation location, int radius){
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

                BeastChunk chunk = new BeastChunk(location.getWorld().getChunkAt(x, z));

                if(chunk.getFaction().equals(this)){
                    chunks.add(chunk);
                }
            }
        }

        //get chunks in a negative radius
        for (int x = finalXNegative; x <= locChunk.getX(); x++) {
            for (int z = finalZNegative; z <= locChunk.getZ(); z++) {
                BeastChunk chunk = new BeastChunk(location.getWorld().getChunkAt(x, z));

                if(chunk.getFaction().equals(this)){
                    chunks.add(chunk);
                }            }
        }


        return chunks;
    }
}
