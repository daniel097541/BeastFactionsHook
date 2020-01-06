package info.beastsoftware.hookcore.manager;

import info.beastsoftware.hookcore.entity.*;

import java.util.Set;

public interface FactionsManager {


    BeastFaction getFactionAtLocation(BeastLocation location);

    BeastFaction getFactionAtChunk(BeastChunk beastChunk);

    String getFactionName(BeastFaction beastFaction);

    BeastFaction getFactionOfPlayer(BeastPlayer beastPlayer);

    Set<BeastPlayer> getMembersOfFaction(BeastFaction beastFaction);

    BeastRelation getRelationOfFactionWith(BeastFaction faction, BeastFaction otherFaction);

    BeastFaction getFromName(String factionName);

    BeastRole getRoleOfPlayer(BeastPlayer player);

    boolean isAdmin(BeastPlayer player);

    boolean isMod(BeastPlayer player);

    boolean isColeader(BeastPlayer player);

    boolean isRecruit(BeastPlayer player);

    boolean hasFaction(BeastPlayer player);

    Set<BeastPlayer> getOnlinePlayersOfFaction(BeastFaction faction);

    Set<BeastFaction> getAllFactions();

    BeastFaction getFromId(String id);
}
