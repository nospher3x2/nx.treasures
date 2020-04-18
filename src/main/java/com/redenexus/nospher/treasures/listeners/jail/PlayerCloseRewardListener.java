package com.redenexus.nospher.treasures.listeners.jail;

import com.redenexus.nospher.treasures.Constants;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.jail.data.Jail;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * @author oNospher
 **/
public class PlayerCloseRewardListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if(event.getInventory().getHolder() instanceof Chest) {
            Chest chest = (Chest) event.getInventory().getHolder();
            Player player = (Player)event.getPlayer();
            if(!player.getWorld().getName().equalsIgnoreCase(Constants.TREASURE_WORLD_NAME)) return;
            player.sendMessage("§cO tesouro foi destruído!");

            for (int i = 0; i < 8; ++i) {
                chest.getWorld().playEffect(chest.getLocation().add(0.0, 1, 0.0), Effect.STEP_SOUND, Material.REDSTONE.ordinal());
            }

            Jail jail = CacheManager.getJailCache().getElement(chest.getLocation());
            if(jail != null) {
                jail.undo();
                CacheManager.getJailCache().removeElement(jail);
            }
        }
    }
}
