package com.redenexus.nospher.treasures.util;

import org.bukkit.ChatColor;

public class ProgressBar {

	public static String getProgressBar(int atual, int maximo, int totalBars, String symbol, String completedColor,
			String notCompletedColor) {

		float percent = (float) atual / maximo;
		int progressBars = (int) ((int) totalBars * percent);
		int leftOver = (totalBars - progressBars);
		
		StringBuilder sb = new StringBuilder();
		sb.append(ChatColor.translateAlternateColorCodes('&', completedColor));
		for (int i = 0; i < progressBars; i++) {
			sb.append(symbol);
		}
		sb.append(ChatColor.translateAlternateColorCodes('&', notCompletedColor));
		for (int i = 0; i < leftOver; i++) {
			sb.append(symbol);
		}
		return sb.toString();
	}

}
