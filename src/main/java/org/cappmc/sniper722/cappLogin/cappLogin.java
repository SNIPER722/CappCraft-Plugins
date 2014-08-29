package org.cappmc.sniper722.cappLogin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by Harry on 28/08/2014.
 */
public class cappLogin extends JavaPlugin{
    public static final Logger log = Logger.getLogger("Minecraft");
    static String maindir = "plugins/cappLogin/";
    public static YamlConfiguration Settings;
    static File SettingsFile = new File(maindir + "config.yml");

    public void onEnable(){

    }

    public void onDisable(){
        Settings = null;
    }





}
