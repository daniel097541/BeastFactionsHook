package info.beastsoftware.hookcore.entity;

import info.beastsoftware.hookcore.manager.FactionsManager;

public interface ManagerAccessor {

    default FactionsManager manager(){
        return FactionsManager.getInstance();
    }

}
