package com.redenexus.nospher.treasures.jail.manager;

import com.boydti.fawe.util.EditSessionBuilder;
import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.jail.data.Jail;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.extent.clipboard.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author oNospher
 **/
public class JailManager {

    @SneakyThrows
    public static void generateJail(Player player, Treasure treasure) {
        Location location = player.getLocation();
        long jailTime = Treasures.getInstance().getConfig().getLong("settings.general.jail-time");

        File file = new File(Treasures.getInstance().getDataFolder(), "/schematic/jail.schematic");
        EditSession session = new EditSessionBuilder(location.getWorld().getName()).fastmode(true).build();
        ClipboardFormat clipboardFormat = ClipboardFormats.findByFile(file);

        if(clipboardFormat == null) {
            System.out.println("[Treasures] Error to load schematic jail.schematic.");
            return;
        }

        clipboardFormat.load(file).paste(session, new Vector(location.getX(), location.getY()-2, location.getZ()), true);;
        session.flushQueue();

        location.getBlock().setType(Material.CHEST);
        location.getBlock().setMetadata("treasureReward", new FixedMetadataValue(Treasures.getInstance(), true));
        Chest chest = (Chest) location.getBlock().getState();
        Inventory inventory = chest.getInventory();

        treasure.getItems().forEach(item -> {
            if(JailManager.percentChance(item.getChance())) {
                inventory.addItem(item.getItemStack());
            }
        });

        Jail jail = new Jail(
                player.getUniqueId(),
                treasure,
                location,
                session,
                System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(jailTime)
        );

        treasure.getBoss().summon(player, treasure.getLevel());

        CacheManager.getJailCache().addElement(jail);
        System.out.println("[Treasures] Jail spawned in " + player.getLocation().getX() + "|" + player.getLocation().getZ());
    }

    private static boolean percentChance(double chance) {
        if (chance < 0.0 || chance > 100.0) {
            throw new IllegalArgumentException("The percentage cannot be greater than 100 nor less than 0.");
        }
        double result = new Random().nextDouble() * 100.0;
        return result <= chance;
    }
}
