package com.redenexus.nospher.treasures.listeners.general;

import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.util.NBTTag;
import com.redenexus.nospher.treasures.jail.manager.JailManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 **/
public class    PlayerInteractAtTreasureListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if (item == null || item.getType() == Material.AIR) return;
        if (!NBTTag.getNBTTag(item).getBoolean("isTreasure")) return;
        String[] preLocation = NBTTag.getNBTTag(item).getString("location").split("@");
        Location location = new Location(Bukkit.getWorld(preLocation[0]), Double.parseDouble(preLocation[1]), Double.parseDouble(preLocation[2]), Double.parseDouble(preLocation[3]));
        if (!player.getWorld().getName().equalsIgnoreCase(preLocation[0])) return;
        if((int) player.getLocation().getX() != (int) location.getX()) return;
        if((int) player.getLocation().getZ() != (int) location.getZ()) return;
        player.setItemInHand(null);
        Integer level = NBTTag.getNBTTag(item).getInt("level");
        Treasure treasure = CacheManager.getTreasureCache().getElement(level);
        player.sendMessage("§6§lTESOURO §fVocê encontrou o tesouro!");
        JailManager.generateJail(player, treasure);
    }
}
