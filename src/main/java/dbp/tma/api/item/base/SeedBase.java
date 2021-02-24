package dbp.tma.api.item.base;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public abstract class SeedBase extends ItemSeeds implements IItemPlantable {
	protected final String modid;

	public SeedBase(String modid, Block crop, Block farmland) {
		super(crop, farmland);
		this.modid = modid;
	}

	public SeedBase(String modid, Block farmland) {
		this(modid, null, farmland);
	}

	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3, Block block) {
		return this.onItemUse(this, item, player, world, x, y, z, u, u1, u2, u3, block);
	}
}
