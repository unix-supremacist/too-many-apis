package dbp.tma.api.item.base;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CropItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class SeedFoodBase extends CropItem implements IItemPlantable {
	protected final String modid;

	public SeedFoodBase(String modid, int heal, float sat, Block crop, Block farmland) {
		super(heal, sat, crop, farmland);
		this.modid = modid;
	}

	public SeedFoodBase(String modid, Block farmland) {
		this(modid, 0, 0, null, farmland);
	}

	public boolean onItemUse(ItemStack item, PlayerEntity player, World world, int x, int y, int z, int u, float u1, float u2, float u3, Block block) {
		return this.onItemUse(this, item, player, world, x, y, z, u, u1, u2, u3, block);
	}
}
