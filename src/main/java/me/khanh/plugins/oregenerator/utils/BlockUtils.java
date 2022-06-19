package me.khanh.plugins.oregenerator.utils;

import com.cryptomorin.xseries.XBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.util.stream.Stream;

public class BlockUtils {
    public static final BlockFace[] FACES;

    public static boolean isBlock(Block b) {
        return true;
    }

    public static boolean isItem(ItemStack i) {
        return i != null && i.getType() != Material.AIR;
    }

    public static boolean isWater(final Material material) {
        return XBlock.isWater(material);
    }

    public static boolean isLava(final Material material) {
        return XBlock.isLava(material);
    }

    public static boolean isSurroundedByWater(Location loc) {
        World world = loc.getWorld();
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        return Stream.of(new Block[] { world.getBlockAt(x + 1, y, z), world.getBlockAt(x - 1, y, z), world.getBlockAt(x, y, z + 1), world.getBlockAt(x, y, z - 1) }).anyMatch(b -> isWater(b.getType()));
    }

    static {
        FACES = new BlockFace[] { BlockFace.SELF, BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
    }
}
