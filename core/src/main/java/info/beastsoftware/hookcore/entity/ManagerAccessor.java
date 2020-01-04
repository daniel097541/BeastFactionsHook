package info.beastsoftware.hookcore.entity;

import info.beastsoftware.hookcore.manager.FactionsManager;
import info.beastsoftware.hookcore.manager.PlayerManager;

public interface ManagerAccessor {

    default FactionsManager manager(){
        return FactionsManager.getInstance();
    }

    default PlayerManager playerManager(){
        return PlayerManager.getInstance();
    }

}
