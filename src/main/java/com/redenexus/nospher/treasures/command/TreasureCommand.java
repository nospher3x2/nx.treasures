package com.redenexus.nospher.treasures.command;

import com.redenexus.nospher.treasures.Constants;
import com.redenexus.nospher.treasures.command.arguments.TreasureGiveCommand;
import com.redenexus.nospher.treasures.command.arguments.TreasureHuntCommand;
import com.redenexus.nospher.treasures.command.arguments.TreasureLeaveCommand;
import com.redenexus.nospher.treasures.command.arguments.TreasureSetPositionCommand;
import com.redenexus.nospher.treasures.util.Helper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author oNospher
 **/
public class TreasureCommand extends Command {

    public TreasureCommand() {
        super("tesouro");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (args.length < 1) {
            if (sender.hasPermission(Constants.COMMAND_PERMISSION + ".give")) {
                sender.sendMessage("§cUtilize /tesouro <caçar/give/setposition> [player] [level]");
                return false;
            }
            sender.sendMessage("§cUtilize /tesouro <caçar/sair>");
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "caçar": {
                return new TreasureHuntCommand().onCommand(sender, Helper.removeFirst(args));
            }
            case "sair": {
                return new TreasureLeaveCommand().onCommand(sender, Helper.removeFirst(args));
            }
            case "give": {
                return new TreasureGiveCommand().onCommand(sender, Helper.removeFirst(args));
            }
            case "setposition": {
                return new TreasureSetPositionCommand().onCommand(sender, Helper.removeFirst(args));
            }
            default: {
                if (sender.hasPermission(Constants.COMMAND_PERMISSION + ".give")) {
                    sender.sendMessage("§cUtilize /tesouro <caçar/give/setposition> [player] [level]");
                    break;
                }
                sender.sendMessage("§cUtilize /tesouro caçar");
            }
        }
        return false;
    }
}
