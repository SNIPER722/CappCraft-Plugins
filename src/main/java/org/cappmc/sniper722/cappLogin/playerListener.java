package org.cappmc.sniper722.cappLogin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by SNIPER722 on 28/08/2014.
 */
public class playerListener implements Listener{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(PlayerPreLoginEvent event){
        if (cappLogin.Settings.getBoolean("General.toggle")){
            String playerName = event.getName();
            boolean result = false;
            try {
                result = Auth.allowJoin(playerName);
            }catch(IOException e){
                cappLogin.log.log(Level.WARNING,"Error connect to Auth Server");
                e.printStackTrace();
            }
            if (result){
                String disconnectMessage = cappLogin.Settings.getString("General.disconnectMessage").replace("{player}",playerName);
                event.setKickMessage(disconnectMessage);
                event.setResult(PlayerPreLoginEvent.Result.KICK_OTHER);
                cappLogin.log.log(Level.INFO," ["+playerName+"]("+event.getAddress().getHostAddress()+") USE Wrong Launcher!");
            }
        }
    }
}
