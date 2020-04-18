package com.redenexus.nospher.treasures.util;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 **/
public class NBTTag {

    public static NBTTagCompound getNBTTag(ItemStack is) {
        net.minecraft.server.v1_8_R3.ItemStack ism = CraftItemStack.asNMSCopy(is);
        if (ism.hasTag()) return ism.getTag();
        return new NBTTagCompound();
    }

    public static ItemStack setNBTTag(ItemStack is, NBTTagCompound ntb) {
        net.minecraft.server.v1_8_R3.ItemStack ism = CraftItemStack.asNMSCopy(is);
        ism.setTag(ntb);
        return CraftItemStack.asBukkitCopy(ism);
    }

    public static boolean hasTag(ItemStack is) {
        return CraftItemStack.asNMSCopy(is).hasTag();
    }

    public static boolean hasKey(ItemStack is, String key) {
        return hasTag(is) && getNBTTag(is).hasKey(key);
    }
}