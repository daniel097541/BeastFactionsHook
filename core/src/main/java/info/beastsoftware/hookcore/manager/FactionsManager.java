package info.beastsoftware.hookcore.manager;

import info.beastsoftware.hookcore.FactionsHook;
import info.beastsoftware.hookcore.cache.Actioner;
import info.beastsoftware.hookcore.cache.CachedManagerImpl;
import info.beastsoftware.hookcore.entity.*;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class FactionsManager extends CachedManagerImpl<String, BeastFaction> {

    @Getter
    private static FactionsManager instance;

    private final PlayerManager playerManager = PlayerManager.getInstance();

    private final FactionsHook hook;

    public FactionsManager(FactionsHook hook) {
        super(new Actioner<String, BeastFaction>() {
            @Override
            public BeastFaction spawn(String identifier) {
                return new BeastFaction(identifier);
            }

            @Override
            public void destroyByIdentifier(String identifier) {

            }
        });
        this.hook = hook;
        instance = this;
    }

    

    public BeastFaction getFactionAtLocation(BeastLocation location) {
        return this.get(hook.getIdOfFactionAtLocation(location.getBukkitLocation()));
    }

    public BeastFaction getFactionAtChunk(BeastChunk beastChunk) {
        return this.get(hook.getFactionAtChunk(beastChunk.getBukkitChunk()));
    }

    public String getFactionName(BeastFaction beastFaction) {
        return this.getHook().getFactionNameFromId(beastFaction.getId());
    }

    public BeastFaction getFactionOfPlayer(BeastPlayer beastPlayer) {
        return this.get(this.getHook().getFactionOfPlayer(beastPlayer.getOfflinePlayer()));
    }

    public Set<BeastPlayer> getMembersOfFaction(BeastFaction beastFaction) {
        return this.getHook().getMembersOfFaction(beastFaction.getId())
                .stream()
                .map(p -> playerManager.get(p.getUniqueId()))
                .collect(Collectors.toSet());
    }

    public BeastRelation getRelationOfFactionWith(BeastFaction faction, BeastFaction otherFaction) {
        return new BeastRelation(this.hook.getRelationOfFactionWithFaction(faction.getId(), otherFaction.getId()));
    }
}
