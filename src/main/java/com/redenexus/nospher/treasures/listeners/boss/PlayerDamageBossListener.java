package com.redenexus.nospher.treasures.listeners.boss;

import com.redenexus.nospher.treasures.jail.data.Jail;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.util.Actionbar;
import com.redenexus.nospher.treasures.util.ProgressBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author oNospher
 **/
public class PlayerDamageBossListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            LivingEntity entity = (LivingEntity) event.getEntity();
            Jail jail = CacheManager.getJailCache().getElement(entity.getLocation());
            if(jail == null) return;
            String life = ProgressBar.getProgressBar((int) entity.getHealth(), (int) entity.getMaxHealth(), 8, "⬛", "§a", "§c");
            Actionbar.getInstance().sendActionBar(player, life + " §a" + (int) entity.getHealth() + "§7/§c" + (int) entity.getMaxHealth());
        }
    }
}
