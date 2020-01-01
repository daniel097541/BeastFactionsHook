package info.beastsoftware.hookcore.manager;

import com.google.common.cache.LoadingCache;
import info.beastsoftware.hookcore.cache.Actioner;
import info.beastsoftware.hookcore.cache.CachedManagerImpl;
import info.beastsoftware.hookcore.entity.BeastPlayer;
import lombok.Getter;

import java.util.UUID;

public class PlayerManager extends CachedManagerImpl<UUID, BeastPlayer> {

    @Getter
    private static PlayerManager instance = new PlayerManager();



    public PlayerManager() {
        super(new Actioner<UUID, BeastPlayer>() {
            @Override
            public BeastPlayer spawn(UUID identifier) {
                return new BeastPlayer(identifier);
            }

            @Override
            public void destroyByIdentifier(UUID identifier) {

            }
        });

        instance = this;
    }
}
