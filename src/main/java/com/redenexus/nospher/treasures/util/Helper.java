package com.redenexus.nospher.treasures.util;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.List;

/**
 * @author oNospher
 **/
public class Helper {

    public static Boolean isInteger(String string) {
        return Integer.getInteger(string) == null;
    }

    public static List<String> fromArray(String... array) {
        List<String> results = Lists.newArrayList();

        Collections.addAll(results, array);

        results.remove("");

        return results;
    }

    public static String[] removeFirst(String[] args) {
        List<String> out = Helper.fromArray(args);

        if (!out.isEmpty())
            out.remove(0);

        return Helper.toArray(out);
    }

    public static String[] toArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
