package com.redenexus.nospher.treasures.command.arguments;

import com.redenexus.nospher.treasures.command.impl.SubCommand;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.treasure.manager.TreasureManager;
import com.redenexus.nospher.treasures.util.Helper;
import com.redenexus.nospher.treasures.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author oNospher
 **/
public class TreasureGiveCommand implements SubCommand {

    public boolean onCommand(CommandSender sender, String[] args) {
        if(!sender.hasPermission(Constants.COMMAND_PERMISSION + ".give")) {
            sender.sendMessage(Constants.INSUFFICIENT_PERMISSION);
            return false;
        }

        if(args.length != 2) {
            sender.sendMessage("§c/tesouro give <player> <level>");
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        String preLevel = args[1];

        if(target == null || !target.isOnline()) {
            sender.sendMessage("§cEste jogador está offline.");
            return false;
        }
        if(!Helper.isInteger(preLevel)) {
            sender.sendMessage("§cVocê inseriu um level inválido.");
            return false;
        }
        Integer level = Integer.parseInt(args[1]);
        Treasure treasure = CacheManager.getTreasureCache().getElement(level);
        if(treasure == null) {
            sender.sendMessage("§cEsse tesouro não existe.");
            return false;
        }
        target.getInventory().addItem(TreasureManager.generateBook(level));
        sender.sendMessage("§eVocê enviou com sucesso um tesouro level " + preLevel + " para '" + target.getName() + "'.");
        return false;
    }
}
