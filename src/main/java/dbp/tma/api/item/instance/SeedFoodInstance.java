package dbp.tma.api.item.instance;

import net.minecraft.block.Block;

public class SeedFoodInstance extends FoodInstance{
	private final Block crop;

	public SeedFoodInstance(String name, Block crop, int healing, int saturation) {
		super(name, healing, saturation);
		this.crop = crop;
	}

	public Block getCrop() {
		return this.crop;
	}
}
