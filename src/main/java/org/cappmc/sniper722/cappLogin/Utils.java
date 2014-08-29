package org.cappmc.sniper722.cappLogin;

import org.bukkit.entity.Player;

/**
 * Created by SNIPER722 on 29/08/2014.
 */
public class Utils {

    public static String getTime(){
        String time = "2014-08-29";
        //TODO: return a String format of MySQL Time format yyyy-mm-dd hh:mm:ss
        return time;
    }

    public static boolean isVisitor(Player player){
        boolean result = true;
        String playerName = player.getName();
        if (!playerName.toLowerCase().startsWith("visitor")){
            result = false;
        }
        return result;// true for visitor false else
    }
}
