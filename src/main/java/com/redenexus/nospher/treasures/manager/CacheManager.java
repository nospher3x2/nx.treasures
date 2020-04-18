package com.redenexus.nospher.treasures.manager;

import com.redenexus.nospher.treasures.treasure.cache.TreasureCache;
import com.redenexus.nospher.treasures.treasure.data.Treasure;
import com.redenexus.nospher.treasures.boss.cache.BossCache;
import com.redenexus.nospher.treasures.boss.data.Boss;
import com.redenexus.nospher.treasures.jail.cache.JailCache;
import com.redenexus.nospher.treasures.jail.data.Jail;

/**
 * @author oNospher
 **/
public class CacheManager {

    public static BossCache<Boss> bossCache;
    public static TreasureCache<Treasure> treasureCache;
    public static JailCache<Jail> jailCache;

    public CacheManager() {
        bossCache = new BossCache<>();
        treasureCache = new TreasureCache<>();
        jailCache = new JailCache<>();
    }

    public static BossCache<Boss> getBossCache() {
        return bossCache;
    }

    public static TreasureCache<Treasure> getTreasureCache() {
        return treasureCache;
    }

    public static JailCache<Jail> getJailCache() {
        return jailCache;
    }
}
