package org.cappmc.sniper722.cappLogin;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Created by SNIPER722 on 29/08/2014.
 */
public class Utils {

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static boolean isVisitor(Player player){
        boolean result = true;
        String playerName = player.getName();
        if (!playerName.toLowerCase().startsWith("visitor")){
            result = false;
        }
        return result;// true for visitor false else
    }

    public static Connection sqlConnection(){
        Connection conn = null;
        String host = cappLogin.Settings.getString("SQL.url");
        int port = cappLogin.Settings.getInt("SQL.port");
        String table = cappLogin.Settings.getString("SQL.table");
        String user = cappLogin.Settings.getString("SQL.user");
        String password = cappLogin.Settings.getString("SQL.password");
        try{
           conn =  DriverManager.getConnection("jdbc:mysql://" + host + ";" + port + "/" + table, user, password);
        }catch(SQLException e){
            cappLogin.log.log(Level.WARNING,"Fail to connect to Database",e);
        }

        return conn;
    }

    public static InputStream phpconnection(String url) throws  {
        try {
            URL u = new URL(url);
            URLConnection c = u.openConnection();
            InputStream r = c.getInputStream();
        }catch(MalformedURLException e){
            cappLogin.log.log(Level.WARNING,"wrong URL!",e);
        }catch (IOException e) {
            cappLogin.log.log(Level.WARNING,"IOE!",e);
        }


    }
}
