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

    public String getName(){
        return this.manager().getFactionName(this);
    }

    public Set<BeastPlayer> getMembers(){
        return this.manager().getMembersOfFaction(this);
    }
}
