package org.cappmc.sniper722.cappLogin;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
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
    // controls
    public static boolean enable = true;
    public static boolean logs = true;
    public static boolean forceLauncher = true;
    public static boolean debugmode = false;
    public static String reason = "Wrong Launcher";

    public void onEnable(){
        new File(maindir).mkdir();
        if (!SettingsFile.exists()){
            log.log(Level.INFO,"File does not exsit");
            try{
                SettingsFile.createNewFile();
                Settings = Config.load(false);
            }catch (IOException e){
                log.log(Level.WARNING,"Error loading config",e);
            }
        }else{
            Settings = Config.load(true);
        }
        // End of loading Settings

        enable = !Settings.getBoolean("Main.killswitch");
        logs = Settings.getBoolean("General.log");
        forceLauncher = Settings.getBoolean("General.forceCappLauncher");
        debugmode = Settings.getBoolean("Other.debug");
        //check the log table is exsit or add table
        if  (logs) {
            //Utils.checkTableExsit();
        }
        // register plugins manager
        PluginManager pm = getServer().getPluginManager();
        // Registers Listener
        pm.registerEvents(new playerListener(), this);

        log.log(Level.INFO,"[cappLogin] cappLogin is loaded");


    }


    public void onDisable(){
        Settings = null;
        enable = false;
        logs = false;
        forceLauncher = false;
        log.log(Level.INFO,"[cappLogin] cappLogin is unloaded");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("capplogin")){
            if (sender.hasPermission("cappLogin.all")||sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage("[cappLogin] is :"+enable);
                }else if(args.length == 1){
                    if (args[0].equals("on")){
                        onEnable();
                        sender.sendMessage("[cappLogin] on");
                        log.log(Level.INFO,"[cappLogin] "+sender.getName()+" reload plugin");
                    }else if(args[0].equals("off")){
                        onDisable();
                        sender.sendMessage("[cappLogin] off");
                        log.log(Level.INFO,"[cappLogin] "+sender.getName()+" reload plugin");
                    }else if(args[0].equals("reload")){
                        onDisable();
                        onEnable();
                        log.log(Level.INFO,"[cappLogin] "+sender.getName()+" reload plugin");
                        sender.sendMessage("[cappLogin] reloaded");
                    }else if(args[0].equals("debug")){
                        debugmode = !debugmode;
                        log.log(Level.INFO,"[cappLogin] "+sender.getName()+"turn debug "+debugmode);
                        sender.sendMessage("[cappLogin] debug mode changed to "+debugmode);

                    }
                }
            }else{
                sender.sendMessage("You do not have permission!");
            }
        }
        return true;
    }

    public static void debugPrint(String MSG) {
        if (debugmode) {
            log.log(Level.INFO, "[cappLogin-debug] " + MSG);
        }
    }

}
