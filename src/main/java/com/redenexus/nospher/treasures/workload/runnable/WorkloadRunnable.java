package com.redenexus.nospher.treasures.workload.runnable;

import com.google.common.collect.Queues;
import com.redenexus.nospher.treasures.workload.impl.Workload;

import java.util.ArrayDeque;

/**
 * @author oNospher
 **/
public class WorkloadRunnable implements Runnable {

    private static final int MAX_MS_PER_TICK = 2;
    private static final ArrayDeque<Workload> workloadDeque = Queues.newArrayDeque();

    public static void addLoad(Workload workload) {
        workloadDeque.add(workload);
    }

    @Override
    public void run() {
        long stopTime = System.currentTimeMillis() + MAX_MS_PER_TICK;
        while(!workloadDeque.isEmpty() && System.currentTimeMillis() <= stopTime) {
           workloadDeque.poll().compute();
        }
    }
}
