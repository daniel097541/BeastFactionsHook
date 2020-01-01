package info.beastsoftware.hookcore;

import info.beastsoftware.hookcore.entity.BeastPlayer;
import info.beastsoftware.hookcore.service.FactionsService;
import info.beastsoftware.hookcore.service.PlayerService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BeastFactionsHook extends JavaPlugin implements Listener {


    private FactionsService factionsService;
    private PlayerService playerService;


    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        factionsService = new FactionsService();
        this.playerService = new PlayerService();
        this.getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();

        if(!player.getName().equalsIgnoreCase("BrutalFiestas")){
            return;
        }

        if(player.getItemInHand().getType().equals(Material.BLAZE_ROD)){

            BeastPlayer beastPlayer = playerService.getFromUUID(player.getUniqueId());
            Bukkit.broadcastMessage(beastPlayer.getMyFaction().toString());
            Bukkit.broadcastMessage(beastPlayer.getFactionAtMyLocation().toString());
            Bukkit.broadcastMessage(beastPlayer.getMyFaction().getRelationWith(beastPlayer.getFactionAtMyLocation()).toString());
        }
    }
}
