package com.redenexus.nospher.treasures.jail.runnable;

import com.redenexus.nospher.treasures.manager.CacheManager;
import com.redenexus.nospher.treasures.jail.data.Jail;

import java.util.Iterator;

/**
 * @author oNospher
 **/
public class JailRefreshRunnable implements Runnable {

    @Override
    public void run() {
        Iterator<Jail> iterator = CacheManager.getJailCache().iterator();

        while(iterator.hasNext()) {
            Jail jail = iterator.next();

            if(jail.canUndo()) {
                if(jail.getPlayer() != null)
                    jail.getPlayer().sendMessage("§cSeu tesouro foi perdido pois o tempo expirou.");

                jail.undo();
                iterator.remove();
            }
        }
    }

}
