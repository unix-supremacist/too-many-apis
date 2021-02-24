package dbp.tma.api.item.base;

import net.minecraft.item.ItemFood;

public abstract class FoodBase extends ItemFood {
	protected final String modid;

	public FoodBase(String modid, int healing, float saturation, boolean wolf) {
		super(healing, saturation, wolf);
		this.modid = modid;
	}

	public FoodBase(String modid, boolean wolf) {
		this(modid, 0, 0, wolf);
	}
}
