package com.redenexus.nospher.treasures.manager;

import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.command.TreasureCommand;
import com.redenexus.nospher.treasures.jail.runnable.JailRefreshRunnable;
import com.redenexus.nospher.treasures.treasure.loader.TreasureLoader;
import com.redenexus.nospher.treasures.util.ClassGetter;
import com.redenexus.nospher.treasures.workload.runnable.WorkloadRunnable;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;

/**
 * @author oNospher
 **/
public class StartManager {

    public StartManager() {
        new ListenerManager();
        new CommandManager();
        new CacheManager();
        new TreasureLoader().loadFromConfig(Treasures.getInstance().getConfig());
        Bukkit.getScheduler().runTaskTimer(Treasures.getInstance(), new JailRefreshRunnable(), 0L, 20L);
        Bukkit.getScheduler().runTaskTimer(Treasures.getInstance(), new WorkloadRunnable(), 0L, 1L);
    }

}

class ListenerManager {
    /**
     * Registering all listeners on call constructor of this class
     */
    ListenerManager() {
        ClassGetter.getClassesForPackage(Treasures.getInstance(), "com.redenexus.nospher.treasures").forEach(clazz -> {
            if (Listener.class.isAssignableFrom(clazz)) {
                try {
                    Listener listener = (Listener) clazz.newInstance();
                    Bukkit.getPluginManager().registerEvents(listener, Treasures.getInstance());
                } catch (InstantiationException | IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}

class CommandManager {
    /**
     * Registering all commands on call constructor of this class
     */
    CommandManager() {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("tesouro", new TreasureCommand());
    }
}


