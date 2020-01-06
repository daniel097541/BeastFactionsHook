package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.entity.BeastFaction;
import info.beastsoftware.hookcore.entity.BeastLocationImpl;
import info.beastsoftware.hookcore.logging.BeastLogger;
import info.beastsoftware.hookcore.manager.FactionsManager;
import info.beastsoftware.hookcore.struct.HookedFactions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.Set;


public interface FactionsService {


    FactionsManager getManager();


    default HookedFactions getHookedFactions() {

        Plugin uuidBasedFactions = Bukkit.getPluginManager().getPlugin("Factions");
        Plugin mcoreBasedFactions = Bukkit.getPluginManager().getPlugin("MassiveCore");

        HookedFactions hookedFactions = null;

        //there is an uuid based factions plugin running
        if (Objects.nonNull(uuidBasedFactions)) {
            // check if it is a savage factions fork
            try {
                Class savageClazz = Class.forName("com.massivecraft.factions.SavageFactions");
                hookedFactions = HookedFactions.SAVAGE;
                BeastLogger.info("&7Hooked into &cSavageFactions &7/ &cSaberFactions &7!");
            }
            //it is not :(  consider it a raw UUID plugin
            catch (ClassNotFoundException e) {
                hookedFactions = HookedFactions.UUID;
                BeastLogger.info("&7Hooked into &cMagicalFactions &7/ &cFactionsUUID &7!");
            }
        }

        // check if it is a mcore factions version
        else if (Objects.nonNull(mcoreBasedFactions)) {
            hookedFactions = HookedFactions.MCORE;
            BeastLogger.info("&7Hooked into &cMCore factions &7!   (i think its time to change this plugin yo)");
        }

        return hookedFactions;
    }

    default boolean isHooked() {
        return Objects.nonNull(this.getManager());
    }

    default BeastFaction getFromId(String id) {
        return this.getManager().getFromId(id);
    }

    default BeastFaction getAtLocation(Location location) {
        return this.getManager().getFactionAtLocation(new BeastLocationImpl(location));
    }

    default BeastFaction getFromName(String factionName) {
        return this.getManager().getFromName(factionName);
    }

    default Set<BeastFaction> getAll() {
        return this.getManager().getAllFactions();
    }

}
