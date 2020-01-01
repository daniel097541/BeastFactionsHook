package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.entity.BeastFaction;
import info.beastsoftware.hookcore.entity.BeastLocation;
import info.beastsoftware.hookcore.logging.BeastLogger;
import info.beastsoftware.hookcore.savage.SavageHook;
import info.beastsoftware.hookcore.FactionsHook;
import info.beastsoftware.hookcore.manager.FactionsManager;
import info.beastsoftware.hookcore.uuid.UUIDHook;
import info.beastsoftware.hookcore.struct.HookedFactions;
import info.beastsoftware.mcorehook.MCoreFactionsHook;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.Set;

@Getter
public class FactionsService {

    private final FactionsManager manager;

    @Getter
    private static final FactionsService instance = new FactionsService();

    public FactionsService() {

        FactionsHook hook = null;

        switch (getHookedFactions()){
            case UUID:
                hook = new UUIDHook(){};
                break;
            case SAVAGE:
                hook = new SavageHook(){};
                break;
            case MCORE:
                hook = new MCoreFactionsHook() {};
                break;
            default:
                break;
        }

        this.manager = new FactionsManager(hook);
    }


    public HookedFactions getHookedFactions(){

        Plugin uuidBasedFactions = Bukkit.getPluginManager().getPlugin("Factions");
        Plugin mcoreBasedFactions = Bukkit.getPluginManager().getPlugin("MassiveCore");

        HookedFactions hookedFactions = null;

        //there is an uuid based factions plugin running
        if(Objects.nonNull(uuidBasedFactions)){
            // check if it is a savage factions fork
            try{
                Class savageClazz = Class.forName("com.massivecraft.factions.SavageFactions");
                hookedFactions = HookedFactions.SAVAGE;
                BeastLogger.info("&7Hooked into &cSavageFactions &7/ &cSaberFactions &7!");
            }
            //it is not :(  consider it a raw UUID plugin
            catch (ClassNotFoundException e){
                hookedFactions = HookedFactions.UUID;
                BeastLogger.info("&7Hooked into &cMagicalFactions &7/ &cFactionsUUID &7!");
            }
        }

        // check if it is a mcore factions version
        else if(Objects.nonNull(mcoreBasedFactions)){
            hookedFactions = HookedFactions.MCORE;
            BeastLogger.info("&7Hooked into &cMCore factions &7!   (i think its time to change this plugin yo)");
        }

        return hookedFactions;
    }

    public BeastFaction getFromId(String id){
        return this.manager.get(id);
    }

    public BeastFaction getAtLocation(Location location){
        return this.manager.getFactionAtLocation(new BeastLocation(location));
    }

    public BeastFaction getFromName(String factionName) {
        return this.manager.getFromName(factionName);
    }
}
