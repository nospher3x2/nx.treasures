package com.redenexus.nospher.treasures.treasure.manager;

import com.redenexus.nospher.treasures.Treasures;
import com.redenexus.nospher.treasures.util.Helper;
import com.redenexus.nospher.treasures.util.NBTTag;
import com.redenexus.nospher.treasures.util.RandomUtil;
import lombok.val;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * @author oNospher
 **/
public class TreasureManager {

    public static ItemStack generateBook(Integer level) {
        val configuration = Treasures.getInstance().getConfig();

        String title = Helper.colorize(configuration.getString("settings." +
                "general." +
                "book." +
                "title"
        ).replace("{level}", String.valueOf(level)));

        String author = Helper.colorize(configuration.getString("settings." +
                "general." +
                "book." +
                "author"
        ));

        final int x = RandomUtil.getRandom(configuration.getInt("settings.general.world.x.min"), configuration.getInt("settings.general.world.x.max"));
        final int z = RandomUtil.getRandom(configuration.getInt("settings.general.world.z.min"), configuration.getInt("settings.general.world.z.max"));

        String page = Helper.colorize(configuration.getString("settings." +
                "general." +
                "book." +
                "page"
        ).replace("{n}", "\n"
        ).replace("{x}", String.valueOf(x)
        ).replace("{z}", String.valueOf(z)));

        String world = configuration.getString("settings." +
                "general." +
                "world." +
                "name");

        ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        bookMeta.setTitle(title);
        bookMeta.setAuthor(author);
        bookMeta.setPages(page);
        itemStack.setItemMeta(bookMeta);

        NBTTagCompound compound = NBTTag.getNBTTag(itemStack);
        compound.setBoolean("isTreasure", true);
        compound.setInt("level", level);
        compound.setString("location", world + "@" + x + "@" + 4 + "@" + z);
        itemStack = NBTTag.setNBTTag(itemStack, compound);
        return itemStack;
    }
}
