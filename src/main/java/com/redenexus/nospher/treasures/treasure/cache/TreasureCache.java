package com.redenexus.nospher.treasures.treasure.cache;

import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.util.builder.CacheBuilder;

/**
 * @author oNospher
 **/
public class TreasureCache<T extends Treasure> extends CacheBuilder<T> {

    @Override
    public <V> T getElement(V value) {
        return this.findOne(treasure -> treasure.getLevel().equals(value));
    }
}
