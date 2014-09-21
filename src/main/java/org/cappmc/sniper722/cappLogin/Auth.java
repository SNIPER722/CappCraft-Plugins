package org.cappmc.sniper722.cappLogin;

import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by SNIPR722 on 28/08/2014.
 */
public class Auth {

    public static boolean allowJoin(String playerName)throws IOException{
        final String REGIST = "Registry -c=McServer -n=";
        final String TAG= "McUserVerify";
        final String URL = cappLogin.Settings.getString("Auth.url");
        final int PORT = cappLogin.Settings.getInt("Auth.port");
        String[] temparray;
        boolean result = false;
        String tempResult;
        Socket connectionsocket = new Socket(URL,PORT);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionsocket.getInputStream()));
        DataOutputStream outToServer = new DataOutputStream( connectionsocket.getOutputStream());
        outToServer.writeBytes(REGIST+playerName+"\r\n");
        tempResult = inFromServer.readLine();
        temparray = tempResult.split(":");
        if (temparray[1].startsWith("true")){
            tempResult = inFromServer.readLine();
            temparray = tempResult.split(":");
            if (temparray[1].startsWith("true")){
                outToServer.writeBytes(TAG+" "+playerName+"\r\n");
                tempResult = inFromServer.readLine();
                if (temparray[1].startsWith("true")){
                    result = true;
                }
            }
        }
        outToServer.close();
        inFromServer.close();
        connectionsocket.close();
        //return result;
        return result;
    }
    }

