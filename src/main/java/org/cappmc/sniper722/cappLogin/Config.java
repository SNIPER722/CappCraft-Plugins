package org.cappmc.sniper722.cappLogin;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by SNIPER722 on 28/08/2014.
 */
public class Config {
    public static YamlConfiguration Config;

    public static void SetDeafult(String path,Object value){
        Config.set(path,Config.get(path,value));
    }

    public static YamlConfiguration load(boolean exsit){
        String dir = "plugins/cappLogin/";
        File Settings = new File(dir + "config.yml");
        try{
            Config = new YamlConfiguration();
            if (exsit){
                Config.load(Settings);
            }
            SetDeafult("Main.killswitch",false);
            SetDeafult("General.forceCappLauncher",true);
            SetDeafult("General.log",true);
            //SetDeafult("General.disconnectMessage","$1{player}, You use the wrong launcher!");
            //SetDeafult("General.guestPermissionConsoleCommand","say hello {player}");
            SetDeafult("Auth.url","127.0.0.1");
            SetDeafult("Auth.port",8080);
            SetDeafult("SQL.url","127.0.0.1");
            SetDeafult("SQL.port",3306);
            SetDeafult("SQL.database","capplog");
            SetDeafult("SQL.table","log");
            SetDeafult("SQL.user","root");
            SetDeafult("SQL.password","password");
            SetDeafult("Other.debug",false);
            Config.save(Settings);
            //case new config
            return Config;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(InvalidConfigurationException e){
            cappLogin.log.log(Level.WARNING,"Invalid config, Please Check!");
            e.printStackTrace();
        }
        // case error
        return null;
    }
}
