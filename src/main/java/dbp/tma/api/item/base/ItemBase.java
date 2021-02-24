package dbp.tma.api.item.base;

import net.minecraft.item.Item;

public abstract class ItemBase extends Item {
	protected final String modid;

	public ItemBase(String modid) {
		this.modid = modid;
	}

	public String getModid() {
		return this.modid;
	}
}
