package me.khanh.plugins.oregenerator.listeners;

import com.cryptomorin.xseries.XBlock;
import me.khanh.plugins.oregenerator.utils.BlockUtils;
import me.khanh.plugins.oregenerator.utils.LoggerUtils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromToListener implements Listener {
    private boolean canGenerate(final Material material, final Block b) {
        boolean check = BlockUtils.isWater(material);
        for (BlockFace face : BlockUtils.FACES) {
            Material type = b.getRelative(face).getType();
            if (((check && BlockUtils.isLava(type)) || (!check && BlockUtils.isWater(type)))/*&& true*/) {
                return true;
            }
        }
        return false;
    }

    private boolean canGenerateBlock(final Block src, final Block to) {
        final Material material = src.getType();
        for (final BlockFace face : BlockUtils.FACES) {
            final Block check = to.getRelative(face);
            if (BlockUtils.isBlock(check) && (BlockUtils.isWater(material)) /*&& config.getBoolean("mode.waterBlock")*/) {
                return true;
            }
            else if (BlockUtils.isBlock(check) && (BlockUtils.isLava(material))/*&& config.getBoolean("mode.lavaBlock")*/) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onBlock(BlockFromToEvent event){
        Block source = event.getBlock();
        Block to = event.getToBlock();
        Material sourceMaterial = source.getType();
        Material toMaterial = to.getType();
        World world = source.getWorld();

        if (sourceMaterial == Material.AIR)
            return;

        if (BlockUtils.isWater(sourceMaterial) || BlockUtils.isLava(sourceMaterial)){
            if ((toMaterial.equals(Material.AIR) || BlockUtils.isWater(toMaterial)) && XBlock.isWaterStationary(source) && BlockUtils.isBlock(source) && canGenerate(sourceMaterial, to) && event.getFace() != BlockFace.DOWN){
                if (BlockUtils.isLava(sourceMaterial) && !BlockUtils.isSurroundedByWater(to.getLocation())) {
                    return;
                }
                event.setCancelled(true);
                LoggerUtils.info("Generate");
            } else if (canGenerateBlock(source, to)){
                event.setCancelled(true);
                LoggerUtils.info("Generate");
            }
        }
    }
}
