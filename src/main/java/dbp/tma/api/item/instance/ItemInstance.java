package dbp.tma.api.item.instance;

import net.minecraft.util.IIcon;

public class ItemInstance {
	protected final String name;
	protected IIcon icon;

	public ItemInstance(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public IIcon getIcon() {
		return this.icon;
	}

	public void setIcon(IIcon icon) {
		this.icon = icon;
	}
}
