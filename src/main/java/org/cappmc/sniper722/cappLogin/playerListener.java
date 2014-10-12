package org.cappmc.sniper722.cappLogin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by SNIPER722 on 28/08/2014.
 */
public class playerListener implements Listener{

    /*public void onPlayerExit(PlayerQuitEvent event){
        if (cappLogin.logs) {
            Utils.playerLog(event.getPlayer(),false);
        }
    }
    */
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerLoginEvent event){
        /*if (cappLogin.logs) {
            Utils.playerLog(event.getPlayer(),true);

        }*/
        boolean result = false;
        if (cappLogin.Settings.getBoolean("General.forceCappLauncher")){
            String playerName= event.getPlayer().getName();

            boolean serverFail = false;
            String disconnectMessage = "null";
            cappLogin.debugPrint("player try to connect to Server ["+playerName+"]");
            try {
                result = Auth.allowJoin(playerName);
            }catch(IOException e){
                cappLogin.log.log(Level.WARNING,"Error connect to Auth Server");
                serverFail = true;
                e.printStackTrace();
            }
            if (!result){
                if (serverFail) {
                    disconnectMessage = "Auth Server fail Please contact Admin!";
                }else{
                    disconnectMessage = cappLogin.reason;
                }
                event.setKickMessage(disconnectMessage);
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                cappLogin.log.log(Level.INFO," ["+playerName+"]("+event.getAddress().getHostAddress()+") "+cappLogin.reason);
            }
        }

        /*if (Utils.isVisitor(event.getPlayer())&&result) {
            String command = cappLogin.Settings.getString("General.guestPermissionConsoleCommand").replace("{player}", event.getPlayer().getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
        }*/

    }




}
