package com.redenexus.nospher.treasures.command.arguments;

import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.command.impl.SubCommand;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.jail.data.Jail;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author oNospher
 **/
public class TreasureLeaveCommand implements SubCommand {

    public boolean onCommand(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cVocê precisa ser um jogador para executar este comando.");
            return false;
        }
        Player player = (Player) sender;
        Jail jail = CacheManager.getJailCache().getElement(Bukkit.getPlayer(sender.getName()));
        if(jail != null) {
            jail.undo();
            CacheManager.getJailCache().removeElement(jail);
            String[] preLeave = Treasures.getInstance().getConfig().getString("settings.general.leave").split("@");
            Location location = new Location(Bukkit.getWorld(preLeave[0]), Double.parseDouble(preLeave[1]), Double.parseDouble(preLeave[2]), Double.parseDouble(preLeave[3]));
            player.teleport(location);
            sender.sendMessage("§aVocê saiu da sua caça.");
        } else {
            sender.sendMessage("§cVocê não abriu uma gaiola, use /spawn.");
        }
        return false;
    }

}
