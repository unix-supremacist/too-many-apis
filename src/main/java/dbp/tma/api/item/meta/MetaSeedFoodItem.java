package dbp.tma.api.item.meta;

import dbp.tma.api.item.base.SeedFoodBase;
import dbp.tma.api.item.instance.SeedFoodInstance;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Texture;
import net.minecraft.client.TextureRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.itemgroup.ItemGroup;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class MetaSeedFoodItem extends SeedFoodBase implements IMetaItem {
	protected static final String TYPE_NAME = "seedfood";
	protected final HashMap<Integer, SeedFoodInstance> items = new HashMap<>();
	@Environment(EnvType.CLIENT)
	protected Texture[] icons;

	public MetaSeedFoodItem(String modid, Block farmland) {
		super(modid, farmland);
		//this.setHasSubtypes(true);
	}

	public MetaSeedFoodItem(String modid) {
		this(modid, Blocks.FARMLAND);
	}

	public ItemStack addItem(String name, int meta, Block crop, int hunger, int saturation) {
		this.items.put(meta, new SeedFoodInstance(name, crop, hunger, saturation));
		return new ItemStack(this, 1, meta);
	}

	protected SeedFoodInstance getItem(ItemStack item) {
		return this.items.get(item.getDamage());
	}

	/**
	 * Get hunger
	 *
	 * @param item food being checked
	 * @return the hunger restored
	 */
	@Override
	public int getHungerPoints(ItemStack item) {
		return this.getItem(item).getHunger();
	}

	/**
	 * Get saturation
	 *
	 * @param item food being checked
	 * @return the saturation float
	 */
	@Override
	public float getSaturation(ItemStack item) {
		return this.getItem(item).getSaturation();
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

	@Override
	public boolean method_3355(ItemStack item, PlayerEntity player, World world, int x, int y, int z, int u, float u1, float u2, float u3) {
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, this.getItem(item).getCrop());
	}
}
