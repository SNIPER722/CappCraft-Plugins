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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Created by SNIPER722 on 29/08/2014.
 */
public class Utils {



    public static boolean isVisitor(Player player){
        boolean result = true;
        String playerName = player.getName();
        if (!playerName.toLowerCase().startsWith("visitor")){
            result = false;
        }
        cappLogin.log.log(Level.INFO,playerName +" is a visitor: "+result);
        return result;// true for visitor false else
    }

    private static Connection sqlConnection(){
        Connection conn = null;
        String host = cappLogin.Settings.getString("SQL.url");
        int port = cappLogin.Settings.getInt("SQL.port");
        String db = cappLogin.Settings.getString("SQL.database");
        String user = cappLogin.Settings.getString("SQL.user");
        String password = cappLogin.Settings.getString("SQL.password");
        try{
           conn =  DriverManager.getConnection("jdbc:mysql://" + host + ";" + port + "/" + db+"?autoReconnect=true&user="+user+"&password="+ password);
        }catch(SQLException e){
            cappLogin.log.log(Level.WARNING,"Fail to connect to Database",e);
        }

        return conn;
    }

    public static InputStream phpconnection(String url){
        InputStream result = null;
        try {
            URL u = new URL(url);
            URLConnection c = u.openConnection();
            result = c.getInputStream();
        }catch(MalformedURLException e){
            cappLogin.log.log(Level.WARNING,"wrong URL!",e);
        }catch (IOException e) {
            cappLogin.log.log(Level.WARNING,"IOE!",e);
        }

         return result;
    }


    public static void playerLog(Player player,boolean login){
        String action;
        if (login){
            action = "login";
        }else{
            action = "logout";
        }
        String table = cappLogin.Settings.getString("SQL.table");
        Connection conn = sqlConnection();
        if (cappLogin.logs ){
            if (conn !=null) {
                String command = "INSERT INTO"+table+"VALUES ("+player.getName()+","+player.getAddress().getAddress()+","+getTime()+","+action+")";

                try {
                    Statement str= conn.createStatement();
                    str.execute(command);
                } catch (SQLException e) {
                    cappLogin.log.log(Level.WARNING,"SQLException in playerLog",e);
                }
            }else{
                cappLogin.log.log(Level.WARNING,"connection is NULL in playerLog");
            }
        }

    }

    public static void checkTableExsit(){
        Connection conn = sqlConnection();
        String table = cappLogin.Settings.getString("SQL.table");
        if (conn !=null) {
            String command = "CREATE TABLE " + table + " IF NOT EXSIT VALUES(Player VARCHAR(20),IP_Address VARCHAR(50),Time DATETIME(),Action VARCHAR(20))";
            try {
                Statement str= conn.createStatement();
                str.execute(command);
            } catch (SQLException e) {
                cappLogin.log.log(Level.WARNING,"SQLException in checkTableExsit",e);
            }
        }else{
            cappLogin.log.log(Level.WARNING,"connection is NULL in checkTableExsit");
        }

    }

    private static String getTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
