package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.FactionsHook;
import info.beastsoftware.hookcore.manager.FactionsManager;
import info.beastsoftware.hookcore.manager.FactionsManagerImpl;
import info.beastsoftware.hookcore.savage.SavageHook;
import info.beastsoftware.hookcore.struct.HookedFactions;
import info.beastsoftware.hookcore.uuid.UUIDHook;
import info.beastsoftware.mcorehook.MCoreFactionsHook;
import lombok.Getter;

import java.util.Objects;

@Getter
public class FactionServiceImpl implements FactionsService {

    private final FactionsManager manager;

    @Getter
    private static final FactionsService instance = new FactionServiceImpl();

    public FactionServiceImpl() {

        FactionsHook hook = null;

        HookedFactions hookedFactions = this.getHookedFactions();

        if (Objects.nonNull(hookedFactions)) {

            switch (getHookedFactions()) {
                case UUID:
                    hook = new UUIDHook() {
                    };
                    break;
                case SAVAGE:
                    hook = new SavageHook() {
                    };
                    break;
                case MCORE:
                    hook = new MCoreFactionsHook() {
                    };
                    break;
                default:
                    break;
            }
        }

        this.manager = new FactionsManagerImpl(hook);

    }

}
