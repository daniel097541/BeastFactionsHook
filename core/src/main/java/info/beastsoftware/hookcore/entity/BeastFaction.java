package info.beastsoftware.hookcore.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
}
