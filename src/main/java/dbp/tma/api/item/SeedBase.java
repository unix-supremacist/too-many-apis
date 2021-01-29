package dbp.tma.api.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class SeedBase extends ItemSeeds {
    public SeedBase(Block crop, Block farmland) {
        super(crop, farmland);
    }

    public SeedBase(Block crop) {
        super(crop, Blocks.farmland);
    }

    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3, Block block) {
        if (u != 1) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, u, item) && player.canPlayerEdit(x, y + 1, z, u, item)) {
            if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
                world.setBlock(x, y + 1, z, block);
                --item.stackSize;
                return true;
            }
        }
        return false;
    }
}
