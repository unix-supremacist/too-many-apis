package dbp.tma.api.item.meta;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dbp.tma.api.item.base.FoodBase;
import dbp.tma.api.item.instance.FoodInstance;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;

public class MetaFoodItem extends FoodBase implements IMetaItem {
	protected static final String TYPE_NAME = "food";
	protected final HashMap<Integer, FoodInstance> items = new HashMap<>();
	protected int lastID = 0;

	@SideOnly(Side.CLIENT)

	public MetaFoodItem(String modid, boolean wolf) {
		super(modid, wolf);
		this.setHasSubtypes(true);
	}

	public MetaFoodItem(String modid) {
		this(modid, false);
	}

	public ItemStack addItem(String name, int hunger, int saturation) {
		int meta = this.lastID++;
		this.items.put(meta, new FoodInstance(name, hunger, saturation));
		return new ItemStack(this, 1, meta);
	}

	protected FoodInstance getItem(ItemStack item) {
		return this.items.get(item.getItemDamage());
	}

	/**
	 * Get hunger
	 *
	 * @param item food being checked
	 * @return the hunger restored
	 */
	@Override
	public int func_150905_g(ItemStack item) {
		return this.getItem(item).getHunger();
	}

	/**
	 * Get saturation
	 *
	 * @param item food being checked
	 * @return the saturation float
	 */
	@Override
	public float func_150906_h(ItemStack item) {
		return this.getItem(item).getSaturation();
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return this.getUnlocalizedName(this.modid, TYPE_NAME, this.getItem(item).getName());
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		this.getSubItems(this, this.items, itemList);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.registerIcons(this.modid, this.items, TYPE_NAME, register);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.getIconFromDamage(this.items, meta);
	}
}
