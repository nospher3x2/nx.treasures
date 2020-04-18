package com.redenexus.nospher.treasures.boss.cache;

import com.redenexus.nospher.treasures.boss.data.Boss;
import com.redenexus.nospher.treasures.util.builder.CacheBuilder;

/**
 * @author oNospher
 **/
public class BossCache<T extends Boss> extends CacheBuilder<T> {

    @Override
    public <V> T getElement(V value) {
        return this.findOne(boss -> boss.getName().equalsIgnoreCase(value.toString()));
    }
}
