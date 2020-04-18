package com.redenexus.nospher.treasures.jail.cache;

import com.redenexus.nospher.treasures.jail.data.Jail;
import com.redenexus.nospher.treasures.util.builder.CacheBuilder;
import com.sk89q.worldedit.Vector;
import org.bukkit.Location;


/**
 * @author oNospher
 **/
public class JailCache<T extends Jail> extends CacheBuilder<T> {

    @Override
    public <V> T getElement(V value) {
        return this.findOne(jail -> jail.getPlayer().equals(value));
    }

    public <V extends Location> T getElement(V value) {
        Vector vector = new Vector(value.getX(), value.getY(), value.getX());
        return this.findOne(jail -> jail.getEditSession().contains(vector));
    }

}
