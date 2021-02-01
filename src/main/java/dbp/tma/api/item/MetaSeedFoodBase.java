package dbp.tma.api.item;

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
import java.util.Map;

public class MetaSeedFoodBase extends SeedFoodBase{
	protected final HashMap<Integer, Integer> HealAmounts = new HashMap<>();
	protected final HashMap<Integer, Float> SatAmounts = new HashMap<>();
	protected final HashMap<Integer, Block> crops = new HashMap<>();
	protected HashMap<Integer, String> items = new HashMap<>();
	protected IIcon[] icon;
	protected String modid;
	protected int iconSize = 255;

	public MetaSeedFoodBase(String modid, Block farmland) {
		super(0, farmland);
		this.modid = modid;
		setHasSubtypes(true);
	}

	public MetaSeedFoodBase(String modid) {
		this(modid, Blocks.farmland);
	}

	@Override
	public int func_150905_g(ItemStack item) {
		if (HealAmounts.containsKey(item.getItemDamage())) return HealAmounts.get(item.getItemDamage());
		return 0;
	}

	@Override
	public float func_150906_h(ItemStack item) {
		if (SatAmounts.containsKey(item.getItemDamage())) return SatAmounts.get(item.getItemDamage());
		return 0.0F;
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return "item." + items.get(item.getItemDamage());
	}

	public MetaSeedFoodBase addItem(String name, int id) {
		items.put(id, name);
		return this;
	}

	public MetaSeedFoodBase addHeal(int heal, int id) {
		HealAmounts.put(id, heal);
		return this;
	}

	public MetaSeedFoodBase addSat(float sat, int id) {
		SatAmounts.put(id, sat);
		return this;
	}

	public MetaSeedFoodBase addCrop(Block crop, int id) {
		crops.put(id, crop);
		return this;
	}

	public MetaSeedFoodBase setIconSize(int size) {
		this.iconSize = size;
		return this;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		for (Map.Entry<Integer, String> i : items.entrySet())
			itemList.add(new ItemStack(this, 1, i.getKey()));
	}

	@Override
	public void registerIcons(IIconRegister register) {
		icon  = new IIcon[iconSize];
		for (Map.Entry<Integer, String> item : items.entrySet())
			icon[item.getKey()] = register.registerIcon(modid + ":item_" + item.getValue());
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3) {
		if (crops.containsKey(this.getDamage(item)))
			return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, crops.get(this.getDamage(item)));
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, Blocks.wheat);
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icon[meta];
	}
}
