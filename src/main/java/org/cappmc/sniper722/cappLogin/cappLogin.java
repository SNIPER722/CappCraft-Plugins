package org.cappmc.sniper722.cappLogin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Harry on 28/08/2014.
 */
public class cappLogin extends JavaPlugin{
    public static final Logger log = Logger.getLogger("Minecraft");
    static String maindir = "plugins/cappLogin/";
    public static YamlConfiguration Settings;
    static File SettingsFile = new File(maindir + "config.yml");
    boolean enable = true;

    public void onEnable(){
        new File(maindir).mkdir();
        if (!SettingsFile.exists()){
            try{
                SettingsFile.createNewFile();
                Settings = Config.load(true);
            }catch (IOException e){
                log.log(Level.WARNING,"Error loading config",e);
            }
        }else{
            Settings = Config.load(false);
        }
        // End of loading Settings

        enable = !Settings.getBoolean("Main.killswitch");
        // register plugins manager
        PluginManager pm = getServer().getPluginManager();
        // Registers Listener
        pm.registerEvents(new playerListener(), this);

        log.log(Level.INFO,"[cappLogin] cappLogin is loaded");


    }


    public void onDisable(){
        Settings = null;
    }





}
