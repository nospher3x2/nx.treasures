package com.redenexus.nospher.treasures.listeners.jail.world;

import com.redenexus.nospher.treasures.Constants;
import com.redenexus.nospher.treasures.jail.data.Jail;
import com.redenexus.nospher.treasures.manager.CacheManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author oNospher
 **/
public class JailPreventionListeners implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(event.getBlock().getLocation().getWorld().getName().equalsIgnoreCase(Constants.TREASURE_WORLD_NAME)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.getBlock().getLocation().getWorld().getName().equalsIgnoreCase(Constants.TREASURE_WORLD_NAME)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        if(event.getEntity().getLocation().getWorld().getName().equalsIgnoreCase(Constants.TREASURE_WORLD_NAME)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Jail jail = CacheManager.getJailCache().getElement(player);
        if(jail != null) {
            player.sendMessage("§cSeu tesouro foi perdido pois você morreu.");
            jail.undo();
            CacheManager.getJailCache().removeElement(jail);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        Jail jail = CacheManager.getJailCache().getElement(player);
        if(jail == null) return;
        if(command.startsWith("/") && !command.equalsIgnoreCase("/tesouro sair")) {
            event.setCancelled(true);
            player.sendMessage("§cVocê não pode executar comandos dentro de uma gaiola, você pode sair utilizando /tesouro sair, mas lembre-se, a sua gaiola irá desaparecer caso você seja dono dela.");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Jail jail = CacheManager.getJailCache().getElement(player);
        if(jail != null) {
            jail.undo();
            CacheManager.getJailCache().removeElement(jail);
        }
    }


}
