package com.redenexus.nospher.treasures.listeners.boss;

import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.jail.data.Jail;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * @author oNospher
 **/
public class BossDeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if(event.getEntity() != null) {
            if(event.getEntity().getKiller() == null) return;
            LivingEntity livingEntity = event.getEntity();
            Jail jail = CacheManager.getJailCache().getElement(livingEntity.getLocation());
            if(jail == null) return;
            if(!(event.getEntity() instanceof Player)) {
                event.setDroppedExp(0);
                event.getDrops().clear();
            }
        }
    }
}
