package me.khanh.plugins.oregenerator.listeners;

import com.cryptomorin.xseries.XBlock;
import me.khanh.plugins.oregenerator.utils.BlockUtils;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromToListener implements Listener {
    private boolean canGenerate(final Material material, final Block b) {
        final boolean check = BlockUtils.isWater(material);
        for (final BlockFace face : BlockUtils.FACES) {
            final Material type = b.getRelative(face).getType();
//            if (((check && BlockUtils.isLava(type)) || (!check && isWater(type)))
//                    && config.getBoolean("mode.waterLava")) {
//                return true;
//            }
            return true;
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
            if ((toMaterial.equals(Material.AIR) || BlockUtils.isWater(toMaterial))
            && XBlock.isWaterStationary(source)
            && BlockUtils.isBlock(source)
            && canGenerate(sourceMaterial, to)
            && event.getFace() != BlockFace.DOWN){
                if (BlockUtils.isLava(sourceMaterial) && !BlockUtils.isSurroundedByWater(to.getLocation())) {
                    return;
                }
                
            }
        }
    }
}
