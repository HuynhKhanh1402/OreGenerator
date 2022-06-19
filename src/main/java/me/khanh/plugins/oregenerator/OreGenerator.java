package me.khanh.plugins.oregenerator;

import me.khanh.plugins.oregenerator.listeners.BlockFromToListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class OreGenerator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new BlockFromToListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
