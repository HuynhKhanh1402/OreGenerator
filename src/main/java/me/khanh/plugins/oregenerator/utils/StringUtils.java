package me.khanh.plugins.oregenerator.utils;

import net.md_5.bungee.api.ChatColor;

public class StringUtils {
    public static String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
