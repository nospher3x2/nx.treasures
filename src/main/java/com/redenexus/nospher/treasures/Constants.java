package com.redenexus.nospher.treasures;

/**
 * @author oNospher
 **/
public class Constants {

    public static String COMMAND_PERMISSION = "h4_treasures.command.treasure";
    public static String INSUFFICIENT_PERMISSION = "§cVocê não possui permissão suficiente para executar este comando.";

    public static String TREASURE_WORLD_NAME;

    static {
        TREASURE_WORLD_NAME = Treasures.getInstance().getConfig().getString("settings.general.world.name");
    }


}
