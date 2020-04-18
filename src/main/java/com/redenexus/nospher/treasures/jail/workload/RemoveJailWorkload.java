package com.redenexus.nospher.treasures.jail.workload;

import com.redenexus.nospher.treasures.workload.impl.Workload;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
public class RemoveJailWorkload implements Workload {

    private final Block block;

    @Override
    public void compute() {
       block.setType(Material.AIR);
    }

}
