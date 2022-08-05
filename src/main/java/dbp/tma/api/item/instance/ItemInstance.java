package dbp.tma.api.item.instance;

import net.minecraft.client.Texture;

public class ItemInstance {
	protected final String name;
	protected Texture icon;

	public ItemInstance(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Texture getIcon() {
		return this.icon;
	}

	public void setIcon(Texture icon) {
		this.icon = icon;
	}
}
