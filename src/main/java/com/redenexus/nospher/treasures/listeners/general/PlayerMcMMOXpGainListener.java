package com.redenexus.nospher.treasures.listeners.general;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.treasure.manager.TreasureManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author oNospher
 **/
public class PlayerMcMMOXpGainListener implements Listener {

    @EventHandler
    public void onGainXP(McMMOPlayerXpGainEvent event) {
        Player player = event.getPlayer();
        int powerLevel = ExperienceAPI.getPowerLevel(player);

        List<Treasure> treasures = CacheManager.getTreasureCache().getElements().stream()
                .filter(Objects::nonNull)
                .filter(treasure -> powerLevel >= treasure.getMcmmo())
                .collect(Collectors.toList());

        if(treasures.isEmpty()) return;
        Treasure getRandom = treasures.get(new Random().nextInt(treasures.size()));
        if(getRandom == null) return;
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber <= getRandom.getChance()) {
            player.getInventory().addItem(TreasureManager.generateBook(getRandom.getLevel()));
            player.sendMessage("§eVocê recebeu um tesouro level " + getRandom.getLevel());
        }
    }
}
