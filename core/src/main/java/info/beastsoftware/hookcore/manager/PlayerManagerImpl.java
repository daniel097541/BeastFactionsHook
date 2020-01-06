package info.beastsoftware.hookcore.manager;

import info.beastsoftware.hookcore.cache.Actioner;
import info.beastsoftware.hookcore.cache.CachedManagerImpl;
import info.beastsoftware.hookcore.entity.BeastPlayer;
import info.beastsoftware.hookcore.entity.BeastPlayerImpl;
import lombok.Getter;

import java.util.UUID;

public class PlayerManagerImpl extends CachedManagerImpl<UUID, BeastPlayer> implements PlayerManager {

    @Getter
    private static PlayerManager instance = new PlayerManagerImpl();

    public PlayerManagerImpl() {
        super(new Actioner<UUID, BeastPlayer>() {
            @Override
            public BeastPlayer spawn(UUID identifier) {
                return new BeastPlayerImpl(identifier);
            }

            @Override
            public void destroyByIdentifier(UUID identifier) {

            }
        });

        instance = this;
    }


    @Override
    public BeastPlayer getPlayer(UUID uuid) {
        return this.get(uuid);
    }
}
