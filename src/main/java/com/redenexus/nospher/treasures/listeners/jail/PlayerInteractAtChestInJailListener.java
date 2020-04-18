package com.redenexus.nospher.treasures.listeners.jail;

import com.redenexus.nospher.treasures.jail.data.Jail;
import com.redenexus.nospher.treasures.manager.CacheManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

/**
 * @author oNospher
 **/
public class PlayerInteractAtChestInJailListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if(block.getType() == Material.CHEST && block.hasMetadata("treasureReward")) {
                Jail jail = CacheManager.getJailCache().getElement(block.getLocation());
                if(jail == null) return;

                Entity entity = jail.getChestLocation().getWorld().getNearbyEntities(block.getLocation(), 50,50,50).stream()
                        .filter(Objects::nonNull)
                        .filter(entity1 -> entity1.hasMetadata("treasureBoss"))
                        .findFirst()
                        .orElse(null);

                if(entity != null) {
                    event.setCancelled(true);
                    player.sendMessage("§cVocê precisa eliminar o boss antes de resgatar o tesouro.");
                }
            }
        }
    }
}
