package com.redenexus.nospher.treasures;

import com.redenexus.nospher.treasures.manager.StartManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author oNospher
 **/
public class Treasures extends JavaPlugin {

    private static Treasures instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        new StartManager();
        new Constants();
    }


    public static Treasures getInstance() {
        return instance;
    }
}
