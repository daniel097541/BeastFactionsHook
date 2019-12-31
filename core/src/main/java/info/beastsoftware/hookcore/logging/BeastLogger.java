package info.beastsoftware.hookcore.logging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public interface BeastLogger {

    String prefix = "&dBeastFactionsHook &7>> ";

    static void info(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

}
