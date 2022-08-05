package dbp.tma.api.item.base;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IItemPlantable {
	default boolean onItemUse(IPlantable plantable, ItemStack item, PlayerEntity player, World world, int x, int y, int z, int u, float u1, float u2, float u3, Block block) {
		boolean itemUsed = false;
		if (u == 1) {
			if (player.canPlayerEdit(x, y, z, u, item) && player.canPlayerEdit(x, y + 1, z, u, item)) {
				if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, plantable) && world.isAirBlock(x, y + 1, z)) {
					world.setBlock(x, y + 1, z, block);
					item.stackSize--;
					itemUsed = true;
				}
			}
		}
		return itemUsed;
	}
}
