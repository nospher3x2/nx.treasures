package com.redenexus.nospher.treasures.command.arguments;

import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.command.impl.SubCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author oNospher
 **/
public class TreasureSetPositionCommand implements SubCommand {

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cVocê precisa ser um jogador para executar este comando.");
            return false;
        }
        if(args.length != 1) {
            sender.sendMessage("§cUtilize /treasure setposition <spawn/leave>");
            return false;
        }
        Player player = (Player) sender;
        Location location = player.getLocation();
        switch (args[0].toLowerCase()) {
            case "spawn":{
                String preSpawn = location.getWorld().getName()  + "@" + location.getX() + "@" + location.getY() + "@" + location.getZ();
                Treasures.getInstance().getConfig().set("settings.general.spawn", preSpawn);
                Treasures.getInstance().saveConfig();
                sender.sendMessage("§aVocê setou a localização de spawn do mundo dos tesouros.");
                return false;
            }
            case "leave":{
                String preLeave = location.getWorld().getName() + "@" + location.getX() + "@" + location.getY() + "@" + location.getZ();
                Treasures.getInstance().getConfig().set("settings.general.leave", preLeave);
                Treasures.getInstance().saveConfig();
                sender.sendMessage("§aVocê setou a localização de saida.");
                return false;
            }
            default:{
                sender.sendMessage("§cUtilize /treasure setposition <spawn/leave>");
                return false;
            }
        }
    }
}
