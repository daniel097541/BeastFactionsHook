package info.beastsoftware.hookcore.entity;

import info.beastsoftware.hookcore.manager.FactionsManager;
import info.beastsoftware.hookcore.manager.FactionsManagerImpl;
import info.beastsoftware.hookcore.manager.PlayerManager;
import info.beastsoftware.hookcore.manager.PlayerManagerImpl;

public interface ManagerAccessor {

    default FactionsManager manager(){
        return FactionsManagerImpl.getInstance();
    }

    default PlayerManager playerManager(){
        return PlayerManagerImpl.getInstance();
    }

}
