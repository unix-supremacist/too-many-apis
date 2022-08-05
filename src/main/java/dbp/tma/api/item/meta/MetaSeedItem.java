package dbp.tma.api.item.meta;

import dbp.tma.api.item.base.SeedBase;
import dbp.tma.api.item.instance.SeedInstance;
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

public class MetaSeedItem extends SeedBase implements IMetaItem {
	protected static final String TYPE_NAME = "seed";
	protected final HashMap<Integer, SeedInstance> items = new HashMap<>();
	protected int lastID = 0;
	@Environment(EnvType.CLIENT)
	protected Texture[] icons;

	public MetaSeedItem(String modid, Block farmland) {
		super(modid, farmland);
		//this.setHasSubtypes(true);
	}

	public MetaSeedItem(String modid) {
		this(modid, Blocks.FARMLAND);
	}

	public ItemStack addItem(String name, Block crop) {
		int meta = this.lastID++;
		this.items.put(meta, new SeedInstance(name, crop));
		return new ItemStack(this, 1, meta);
	}

	protected SeedInstance getItem(ItemStack item) {
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

	@Override
	public boolean method_3355(ItemStack item, PlayerEntity player, World world, int x, int y, int z, int u, float u1, float u2, float u3) {
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, this.getItem(item).getCrop());
	}
}
