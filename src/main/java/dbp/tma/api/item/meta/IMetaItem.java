package dbp.tma.api.item.meta;

import dbp.tma.api.item.instance.ItemInstance;
import net.minecraft.client.Texture;
import net.minecraft.client.TextureRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.List;

public interface IMetaItem {
	default String getUnlocalizedName(String modid, String metaTypeName, String itemName) {
		return "item." + modid + "." + metaTypeName + "." + itemName;
	}

	default void appendItemStacks(Item item, HashMap<Integer, ? extends ItemInstance> items, List itemList) {
		for (Integer id : items.keySet()) {
			itemList.add(new ItemStack(item, 1, id));
		}
	}

	default void registerIcons(String modid, HashMap<Integer, ? extends ItemInstance> items, String metaTypeName, TextureRegistry register) {
		for (ItemInstance item : items.values()) {
			item.setIcon(register.registerTexture(modid + ":item_" + metaTypeName + "_" + item.getName()));
		}
	}

	default Texture getIconFromDamage(HashMap<Integer, ? extends ItemInstance> items, int meta) {
		ItemInstance item = items.getOrDefault(meta, null);
		Texture icon = null;
		if (item != null) {
			icon = item.getIcon();
		}
		return icon;
	}
}
