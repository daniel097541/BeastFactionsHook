package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.entity.BeastFaction;
import info.beastsoftware.hookcore.entity.BeastLocation;
import info.beastsoftware.hookcore.savage.SavageHook;
import info.beastsoftware.hookcore.FactionsHook;
import info.beastsoftware.hookcore.manager.FactionsManager;
import info.beastsoftware.hookcore.uuid.UUIDHook;
import info.beastsoftware.hookcore.struct.HookedFactions;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class FactionsService {

    private final FactionsManager manager;


    public FactionsService() {

        FactionsHook hook = null;

        switch (getHookedFactions()){
            case UUID:
                hook = new UUIDHook();
                break;
            case SAVAGE:
                hook = new SavageHook();
                break;
            default:
                break;
        }

        this.manager = new FactionsManager(hook);
    }


    public HookedFactions getHookedFactions(){
        return HookedFactions.UUID;
    }

    public BeastFaction getFromId(String id){
        return this.manager.get(id);
    }

    public BeastFaction getAtLocation(Location location){
        return this.manager.getFactionAtLocation(new BeastLocation(location));
    }
}
