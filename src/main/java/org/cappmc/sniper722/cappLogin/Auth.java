package org.cappmc.sniper722.cappLogin;

import com.google.gson.Gson;

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
        cappLogin.debugPrint("Player Received: "+playerName);
        Socket connectionsocket = new Socket(URL,PORT);
        cappLogin.debugPrint("Server ["+URL+":"+PORT+"] connected");
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectionsocket.getInputStream()));
        DataOutputStream outToServer = new DataOutputStream( connectionsocket.getOutputStream());
        cappLogin.debugPrint("Sent->"+REGIST+playerName);
        outToServer.writeBytes(REGIST+playerName+"\r\n");
        //cappLogin.debugPrint("waiting Auth Server registration reply!");
        tempResult = inFromServer.readLine();
        cappLogin.debugPrint("return<-"+tempResult);
        temparray = tempResult.split(":");
        if (temparray[1].equals("true}")){
            cappLogin.debugPrint("Sent->"+TAG+" "+playerName);
            outToServer.writeBytes(TAG+" "+playerName+"\r\n");
            tempResult = inFromServer.readLine();
            cappLogin.debugPrint("return<-"+tempResult);
            Gson g = new Gson();
            AuthResult r = g.fromJson(tempResult,AuthResult.class);
            result = r.getAllowJoin();
            cappLogin.reason = r.getError();
        }
        cappLogin.debugPrint("Auth finish result: "+result);
        outToServer.close();
        inFromServer.close();
        connectionsocket.close();
        //return result;
        return result;
    }
    }

