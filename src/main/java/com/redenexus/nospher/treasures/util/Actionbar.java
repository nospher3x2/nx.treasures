package com.redenexus.nospher.treasures.util;

import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Actionbar {

   private static Actionbar instance;

   public static Actionbar getInstance() {
	  return instance == null ? (instance = new Actionbar()) : instance;
   }

   public void sendActionBar(Player p, String msg) {
	  PacketPlayOutChat ppoc = new PacketPlayOutChat(
		ChatSerializer.a("{\"text\": \"" + msg + "\"}"),
		(byte) 2
	  );

	  ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
   }

}
