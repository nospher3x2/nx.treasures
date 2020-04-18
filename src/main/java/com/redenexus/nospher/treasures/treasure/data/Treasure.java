package com.redenexus.nospher.treasures.treasure.data;

import com.redenexus.nospher.treasures.boss.data.Boss;
import com.redenexus.nospher.treasures.treasure.item.Item;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter @Setter
public class Treasure {

    private final Integer level;
    private final Boss boss;
    private final int chance;
    private final int mcmmo;
    private final List<Item> items;

}
