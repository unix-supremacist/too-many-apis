package dbp.tma.api.item.meta;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dbp.tma.api.item.base.SeedFoodBase;
import dbp.tma.api.item.instance.SeedFoodInstance;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class MetaSeedFoodItem extends SeedFoodBase implements IMetaItem {
	protected static final String TYPE_NAME = "seedfood";
	protected final HashMap<Integer, SeedFoodInstance> items = new HashMap<>();
	protected int lastID = 0;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	public MetaSeedFoodItem(String modid, Block farmland) {
		super(modid, farmland);
		this.setHasSubtypes(true);
	}

	public MetaSeedFoodItem(String modid) {
		this(modid, Blocks.farmland);
	}

	public void addItem(String name, Block crop, int healing, int saturation) {
		this.items.put(this.lastID++, new SeedFoodInstance(name, crop, healing, saturation));
	}

	protected SeedFoodInstance getItem(ItemStack item) {
		return this.items.get(item.getItemDamage());
	}

	/**
	 * Get healing
	 *
	 * @param item food being checked
	 * @return the health restored
	 */
	@Override
	public int func_150905_g(ItemStack item) {
		return this.getItem(item).getHealing();
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
		this.icons = this.registerIcons(this.modid, this.items, TYPE_NAME, register);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage) {
		return this.getIconFromDamage(this.icons, damage);
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3) {
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, this.getItem(item).getCrop());
	}
}
