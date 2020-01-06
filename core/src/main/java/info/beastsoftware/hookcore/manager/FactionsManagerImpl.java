package info.beastsoftware.hookcore.manager;

import info.beastsoftware.hookcore.FactionsHook;
import info.beastsoftware.hookcore.cache.Actioner;
import info.beastsoftware.hookcore.cache.CachedManagerImpl;
import info.beastsoftware.hookcore.entity.*;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class FactionsManagerImpl  extends CachedManagerImpl<String, BeastFaction> implements FactionsManager {


    @Getter
    private static FactionsManager instance;

    private final PlayerManager playerManager = PlayerManagerImpl.getInstance();

    private final FactionsHook hook;


    public FactionsManagerImpl(FactionsHook hook) {
        super(new Actioner<String, BeastFaction>() {
            @Override
            public BeastFaction spawn(String identifier) {
                return new BeastFactionImpl(identifier);
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
                .map(p -> playerManager.getPlayer(p.getUniqueId()))
                .collect(Collectors.toSet());
    }

    public BeastRelation getRelationOfFactionWith(BeastFaction faction, BeastFaction otherFaction) {
        return new BeastRelation(this.hook.getRelationOfFactionWithFaction(faction.getId(), otherFaction.getId()));
    }

    public BeastFaction getFromName(String factionName) {
        return this.get(this.getHook().getFactionIdFromName(factionName));
    }

    public BeastRole getRoleOfPlayer(BeastPlayer player) {
        return new BeastRole(this.getHook().getRoleOfPlayer(player.getOfflinePlayer()));
    }

    public boolean isAdmin(BeastPlayer player) {
        return this.getHook().isAdmin(player.getOfflinePlayer());
    }

    public boolean isMod(BeastPlayer player) {
        return this.getHook().isMod(player.getOfflinePlayer());
    }

    public boolean isColeader(BeastPlayer player) {
        return this.getHook().isColeader(player.getOfflinePlayer());
    }

    public boolean isRecruit(BeastPlayer player) {
        return this.getHook().isRecruit(player.getOfflinePlayer());
    }

    public boolean hasFaction(BeastPlayer player) {
        return this.getHook().hasFaction(player.getOfflinePlayer());
    }

    public Set<BeastPlayer> getOnlinePlayersOfFaction(BeastFaction faction) {
        return this.getHook().getOnlinePlayers(faction.getId())
                .stream()
                .map(p -> this.playerManager.getPlayer(p.getUniqueId()))
                .collect(Collectors.toSet());
    }

    public Set<BeastFaction> getAllFactions() {
        return this.hook.getAllFactions()
                .stream()
                .map(this::get)
                .collect(Collectors.toSet());
    }

    @Override
    public BeastFaction getFromId(String id) {
        return this.get(id);
    }

}
