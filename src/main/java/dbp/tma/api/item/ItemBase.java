package dbp.tma.api.item;

import dbp.tma.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBase extends Item {
	public String name;
	public static final HashMap<Integer, String> items = new HashMap<>();
	public IIcon[] icon = new IIcon[255];
	protected String modid;

	public ItemBase(String modid) {
		setHasSubtypes(true);
		this.modid = modid;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		for (int i = 0; i < 255; i++) {
			itemList.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return "item." + items.get(item.getItemDamage()) + name;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		for (Map.Entry<Integer, String> item : items.entrySet()) {
			icon[item.getKey()] = register.registerIcon(Reference.MODID + ":item_" + item.getValue());
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icon[meta];
	}

	public ItemBase setName(String name) {
		this.name = name;
		return this;
	}

	public ItemBase setModid(String modid) {
		this.modid = modid;
		return this;
	}

	public ItemBase addItem(String name, int id) {
		items.put(id, name);
		return this;
	}
}