package org.cappmc.sniper722.cappLogin;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by SNIPER722 on 28/08/2014.
 */
public class Config {
    public static YamlConfiguration Config;

    public static void SetDeafult(String path,Object value){
        Config.set(path,Config.get(path,value));
    }

    public static YamlConfiguration load(boolean exsit){
        String dir = "plugins/cappLogin";
        File Settings = new File(dir + "config.yml");
        try{
            Config = new YamlConfiguration();
            if (exsit){
                Config.load(Settings);
            }
            SetDeafult("General.toggle",true);
            SetDeafult("General.disconnectMessage","$1{player}, You use the wrong launcher!");
            SetDeafult("Auth.url","127.0.0.1");
            SetDeafult("Auth.port","8080");
            SetDeafult("SQL.url","127.0.0.1");
            SetDeafult("SQL.port","3306");
            SetDeafult("SQL.user","root");
            SetDeafult("SQL.password","password");

            //case new config
            return Config;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(InvalidConfigurationException e){
            e.printStackTrace();
        }
        // case error
        return null;
    }
}
