package com.redenexus.nospher.treasures.boss.loader;

import com.redenexus.nospher.treasures.boss.data.Boss;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.util.Helper;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

/**
 * @author oNospher
 **/
public class BossLoader {

    public void loadFromConfig(FileConfiguration configuration) {
        configuration.getConfigurationSection("settings.bosses").getKeys(false).forEach(name -> {
            ConfigurationSection section = configuration.getConfigurationSection("settings.bosses." + name);

            EntityType entityType = EntityType.valueOf(section.getString("entity"));
            String displayName = Helper.colorize(section.getString("displayName"));
            double damage = section.getDouble("damage");
            double health = section.getDouble("health");
            int speed = section.getInt("speed");

            Boss boss = new Boss(
                    name,
                    entityType,
                    displayName,
                    damage,
                    health,
                    speed
            );

            CacheManager.getBossCache().addElement(boss);
            System.out.println("[Treasures] Boss " + name + " loaded.");
        });

        System.out.println("[Treasures] A total of " + CacheManager.getBossCache().size() + " bosses were loaded");
    }
}
