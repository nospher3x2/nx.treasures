package com.redenexus.nospher.treasures.treasure.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter
@Setter
public class Item {

    private final ItemStack itemStack;
    private final double chance;

}
