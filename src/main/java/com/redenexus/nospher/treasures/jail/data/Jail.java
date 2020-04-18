package com.redenexus.nospher.treasures.jail.data;

import com.redenexus.nospher.treasures.jail.workload.RemoveJailWorkload;
import com.redenexus.nospher.treasures.jail.util.NearbyBlocks;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.workload.runnable.WorkloadRunnable;
import com.sk89q.worldedit.EditSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter
public class Jail {

    private final UUID owner;
    private final Treasure treasure;
    private final Location chestLocation;
    private final EditSession editSession;
    private final Long time;

    public boolean canUndo() {
        return System.currentTimeMillis() >= time;
    }

    public void undo() {
        Block blockC = chestLocation.getBlock();
        if(blockC.getState() instanceof Chest) {
            Chest chest = (Chest) blockC.getState();
            for(int i = 0; i < 27; i++) {
                chest.getInventory().setItem(i, null);
            }
        }
        blockC.setTypeId(0);

        this.getChestLocation().getWorld().getNearbyEntities(this.getChestLocation(), 50,50,50).stream()
                .filter(Objects::nonNull)
                .filter(entity1 -> entity1.hasMetadata("treasureBoss"))
                .forEach(Entity::remove);

        NearbyBlocks nearbyBlocks = new NearbyBlocks(this.chestLocation, 50);
        nearbyBlocks.getNearbyBlocks().stream()
                .filter(Objects::nonNull)
                .filter(block -> block.getType() == Material.GLASS)
                .forEach(block -> {
                    RemoveJailWorkload undo = new RemoveJailWorkload(block);
                    WorkloadRunnable.addLoad(undo);
                });

        System.out.println("[Treasures] Jail undone in " + this.chestLocation.getX() + "|" + this.chestLocation.getZ());
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(owner);
    }
}
