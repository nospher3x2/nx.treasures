package com.redenexus.nospher.treasures.util;

import java.util.Random;

/**
 * @author oNospher
 **/
public class RandomUtil {

    public static int getRandom(int min, int max) {
        Random random = new Random();
        int result = random.nextInt(max);
        return Math.max(result, min);
    }
}
