package info.beastsoftware.hookcore.service;

import info.beastsoftware.hookcore.manager.PlayerManager;
import info.beastsoftware.hookcore.manager.PlayerManagerImpl;
import lombok.Getter;

@Getter
public class PlayerServiceImpl implements PlayerService {

    private final PlayerManager manager = PlayerManagerImpl.getInstance();

    @Getter
    private static final PlayerService instance = new PlayerServiceImpl();


}
