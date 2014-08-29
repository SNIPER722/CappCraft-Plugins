package org.cappmc.sniper722.cappLogin;

import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by SNIPEr722 on 28/08/2014.
 */
public class Auth {

    public boolean allowJoin(Player player)throws IOException{
        final String TAG= "McUserVerify";
        boolean result = false;
        String tempResult;
        Socket connectionsocket = new Socket(cappLogin.Settings.getString("Auth.url"),cappLogin.Settings.getInt("Auth.port"));
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionsocket.getInputStream()));
        DataOutputStream outToServer = new DataOutputStream( connectionsocket.getOutputStream());

        outToServer.writeBytes(TAG+";"+player.getName());
        tempResult = inFromServer.readLine();

        if (tempResult.equals("true")){
            result = true;
        }
        return result;
    }
}
