package dbp.tma.api.item.instance;

import net.minecraft.block.Block;

public class SeedInstance extends ItemInstance {
	final protected Block crop;

	public SeedInstance(String name, Block crop) {
		super(name);
		this.crop = crop;
	}

	public Block getCrop() {
		return this.crop;
	}
}
