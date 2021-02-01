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

public class MetaSeedBase extends SeedBase {
	protected IIcon[] icon;
	protected final HashMap<Integer, String> items = new HashMap<>();
	protected final HashMap<Integer, Block> crops = new HashMap<>();
	protected String modid;

	public MetaSeedBase(String modid, Block farmland) {
		super(Blocks.wheat, farmland);
		this.modid = modid;
		setHasSubtypes(true);
	}

	public MetaSeedBase(String modid) {
		this(modid, Blocks.farmland);
	}

	public MetaSeedBase addItem(String name, int id) {
		items.put(id, name);
		return this;
	}

	public MetaSeedBase addCrop(Block crop, int id) {
		crops.put(id, crop);
		return this;
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3) {
		if (crops.containsKey(this.getDamage(item)))
			return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, crops.get(this.getDamage(item)));
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, Blocks.wheat);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return "item." + items.get(item.getItemDamage());
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		for (Map.Entry<Integer, String> i : items.entrySet())
			itemList.add(new ItemStack(this, 1, i.getKey()));
	}

	@Override
	public void registerIcons(IIconRegister register) {
		icon  = new IIcon[items.size()];
		for (Map.Entry<Integer, String> item : items.entrySet())
			icon[item.getKey()] = register.registerIcon(modid + ":item_" + item.getValue());
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icon[meta];
	}
}
