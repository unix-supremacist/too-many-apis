package dbp.tma.api.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class SeedFoodBase extends ItemSeedFood {
	public SeedFoodBase(int heal, float sat, Block crop, Block farmland) {
		super(heal, sat, crop, farmland);
	}

	public SeedFoodBase(int heal, float sat, Block crop) {
		this(heal, sat, crop, Blocks.farmland);
	}

	public SeedFoodBase(int heal, Block crop) {
		this(heal, 0.0F, crop);
	}

	public SeedFoodBase(int heal, Block crop, Block farmland) {
		this(heal, 0.0F, crop, farmland);
	}

	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3, Block block) {
		if (u != 1) {
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
