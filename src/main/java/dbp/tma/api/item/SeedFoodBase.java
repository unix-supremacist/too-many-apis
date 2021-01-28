package dbp.tma.api.item;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;

public class SeedFoodBase extends ItemSeedFood{
	public SeedFoodBase(int heal, float sat, Block crop, Block farmland) {
		super(heal, sat, crop, farmland);
	}
	
	public SeedFoodBase(int heal, float sat, Block crop) {
		super(heal, sat, crop, Blocks.farmland);
	}
	
	public SeedFoodBase(int heal, Block crop) {
		super(heal, 0.0F, crop, Blocks.farmland);
	}
	
	public SeedFoodBase(int heal, Block crop, Block farmland) {
		super(heal, 0.0F, crop, farmland);
	}
}
