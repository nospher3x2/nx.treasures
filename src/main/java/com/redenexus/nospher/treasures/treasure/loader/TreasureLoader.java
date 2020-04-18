package com.redenexus.nospher.treasures.treasure.loader;

import com.google.common.collect.Lists;
import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.boss.data.Boss;
import com.redenexus.nospher.treasures.boss.loader.BossLoader;
import com.redenexus.nospher.treasures.treasure.item.Item;

import com.redenexus.nospher.treasures.util.EnchantmentUtil;
import com.redenexus.nospher.treasures.util.Helper;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * @author oNospher
 **/
public class TreasureLoader {

    public void loadFromConfig(FileConfiguration configuration) {
        new BossLoader().loadFromConfig(configuration);
        configuration.getConfigurationSection("settings.treasures").getKeys(false).forEach(key -> {
            ConfigurationSection section = configuration.getConfigurationSection("settings.treasures." + key);

            int treasureLevel = section.getInt("level");
            Boss boss = CacheManager.getBossCache().getElement(section.getString("boss"));
            int chance = section.getInt("chance");
            int mcmmo = section.getInt("mcmmo");
            List<Item> itemList = Lists.newArrayList();

            section.getConfigurationSection("items").getKeys(false).forEach(itemKey -> {
                ConfigurationSection itemSection = section.getConfigurationSection("items." + itemKey);
                String materialPath = itemSection.getString("material");
                int itemId;
                int itemData;
                if (materialPath.contains(":")) {
                    itemId = Integer.parseInt(materialPath.split(":")[0]);
                    itemData = Integer.parseInt(materialPath.split(":")[1]);
                }
                else {
                    itemId = Integer.parseInt(materialPath);
                    itemData = 0;
                }
                String displayName = Helper.colorize(itemSection.getString("displayName"));
                int amount = itemSection.getInt("amount");
                int itemChance = itemSection.getInt("chance");
                List<String> lore = itemSection.getStringList("lore");
                lore.replaceAll(Helper::colorize);
                ItemStack itemStack = new ItemStack(itemId, amount, (short)itemData);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (!displayName.isEmpty()) itemMeta.setDisplayName(displayName);
                if (!lore.isEmpty()) itemMeta.setLore(lore);
                String enchants = itemSection.getString("enchants");
                if(!enchants.equalsIgnoreCase("none")) {
                    if(enchants.contains(",")) {
                        Arrays.stream(enchants.split(",")).forEach(enchant -> {
                            String enchantString = enchant.split(":")[0];
                            int level = Integer.parseInt(enchant.split(":")[1]);
                            Enchantment enchantment = EnchantmentUtil.getTranslateEnchant(enchantString);
                            if(enchantment != null) itemMeta.addEnchant(enchantment, level, true);
                        });
                    } else {
                        String enchantString = enchants.split(":")[0];
                        int level = Integer.parseInt(enchants.split(":")[1]);
                        Enchantment enchantment = EnchantmentUtil.getTranslateEnchant(enchantString);
                        if(enchantment != null) itemMeta.addEnchant(enchantment, level, true);
                    }
                }
                boolean glow = itemSection.getBoolean("glow");
                    if (glow) {
                        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    }
                itemStack.setItemMeta(itemMeta);
                Item item = new Item(itemStack, itemChance);
                itemList.add(item);
            });

            Treasure treasure = new Treasure(
                    treasureLevel,
                    boss,
                    chance,
                    mcmmo,
                    itemList
            );

            CacheManager.getTreasureCache().addElement(treasure);
            System.out.println("[Treasures] Treasure level " + treasure.getLevel() + " loaded.");
        });
        System.out.println("[Treasures] A total of " + CacheManager.getTreasureCache().size() + " treasures were loaded");
    }
}
