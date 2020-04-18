package com.redenexus.nospher.treasures.command.arguments;

import com.redenexus.nospher.treasures.Constants;
import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.command.impl.SubCommand;
import com.redenexus.nospher.treasures.util.NBTTag;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 **/
public class TreasureHuntCommand implements SubCommand {

    public boolean onCommand(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cVocê precisa ser um jogador para executar este comando.");
            return false;
        }
        Player player = (Player) sender;
        ItemStack item = player.getItemInHand();
        if(player.getWorld().getName().equalsIgnoreCase(Constants.TREASURE_WORLD_NAME)) {
            sender.sendMessage("§cVocê já está caçando tesouros!");
            return false;
        }
        if(item == null || item.getType() == Material.AIR) {
            sender.sendMessage("§cVocê precisa de um livro do tesouro na mão.");
            return false;
        }
        NBTTagCompound compound = NBTTag.getNBTTag(item);
        if(!compound.getBoolean("isTreasure")) {
            sender.sendMessage("§cVocê precisa de um livro do tesouro na mão.");
            return false;
        }
        sender.sendMessage("§aVocê entrou no mundo da caça ao tesouro. Boa sorte na sua caçada.");
        String[] preSpawn = Treasures.getInstance().getConfig().getString("settings.general.spawn").split("@");
        Location location = new Location(Bukkit.getWorld(preSpawn[0]), Double.parseDouble(preSpawn[1]), Double.parseDouble(preSpawn[2]), Double.parseDouble(preSpawn[3]));
        player.teleport(location);
        return false;
    }

}
