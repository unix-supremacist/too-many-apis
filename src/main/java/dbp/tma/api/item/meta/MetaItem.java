package dbp.tma.api.item.meta;

import dbp.tma.api.item.base.ItemBase;
import dbp.tma.api.item.instance.ItemInstance;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Texture;
import net.minecraft.client.TextureRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.itemgroup.ItemGroup;

import java.util.HashMap;
import java.util.List;

public class MetaItem extends ItemBase implements IMetaItem {
	protected static final String TYPE_NAME = "meta";
	protected final HashMap<Integer, ItemInstance> items = new HashMap<>();
	protected int lastID = 0;
	@Environment(EnvType.CLIENT)
	protected Texture[] icons;

	public MetaItem(String modid) {
		super(modid);
		//this.setHasSubtypes(true);
	}

	public ItemStack addItem(String name) {
		int meta = this.lastID++;
		this.items.put(meta, new ItemInstance(name));
		return new ItemStack(this, 1, meta);
	}

	protected ItemInstance getItem(ItemStack item) {
		return this.items.get(item.getDamage());
	}

	@Override
	public String getItemstackTranslatedName(ItemStack item) {
		return this.getUnlocalizedName(this.modid, TYPE_NAME, this.getItem(item).getName());
	}

	@Override
	public void appendItemStacks(Item item, ItemGroup tabs, List itemList) {
		this.appendItemStacks(this, this.items, itemList);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void method_5462(TextureRegistry register) {
		this.registerIcons(this.modid, this.items, TYPE_NAME, register);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Texture method_3343(int meta) {
		return this.getIconFromDamage(this.items, meta);
	}
}
