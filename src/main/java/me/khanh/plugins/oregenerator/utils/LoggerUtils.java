package me.khanh.plugins.oregenerator.utils;

import me.khanh.plugins.oregenerator.OreGenerator;

public class LoggerUtils {
    private static final OreGenerator plugin = OreGenerator.getPlugin(OreGenerator.class);

    public static void info(String message){
        plugin.getLogger().info(StringUtils.color(message));
    }

    public static void warning(String message){
        plugin.getLogger().warning(message);
    }

    public static void severe(String message){
        plugin.getLogger().severe(message);
    }
}
