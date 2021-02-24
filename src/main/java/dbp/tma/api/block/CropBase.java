package dbp.tma.api.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class CropBase extends BlockCrops {
	protected final String modid;
	protected final String name;
	protected final Item dropItem;
	protected final int dropMeta;
	protected final Item seedItem;
	protected final int seedMeta;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[8];

	public CropBase(String modid, String name, Item dropItem, int dropMeta, Item seedItem, int seedMeta) {
		this.modid = modid;
		this.name = name;
		this.dropItem = dropItem;
		this.dropMeta = dropMeta;
		this.seedItem = seedItem;
		this.seedMeta = seedMeta;
	}

	public CropBase(String modid, String name, Item dropItem, int ItemMeta) {
		this(modid, name, dropItem, ItemMeta, dropItem, ItemMeta);
	}

	/**
	 * Get drop item
	 *
	 * @return drop Item
	 */
	@Override
	protected Item func_149865_P() {
		return this.dropItem;
	}

	/**
	 * Get seed item
	 *
	 * @return seed Item
	 */
	@Override
	protected Item func_149866_i() {
		return this.seedItem;
	}

	protected ItemStack getDrop() {
		return new ItemStack(this.dropItem, 1, this.dropMeta);
	}

	protected ItemStack getSeed() {
		return new ItemStack(this.seedItem, 1, this.seedMeta);
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + this.modid + ".crop." + this.name;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int growth) {
		return this.icons[growth];
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		for (int i = 0; i < this.icons.length; ++i) {
			this.icons[i] = icon.registerIcon(this.modid + ":crop_" + this.name + "_" + i);
		}
	}

	public ItemStack getItemstackDropped(int meta, Random random, int fortune) {
		return meta == 7 ? this.getDrop() : this.getSeed();
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		if (meta < 7) {
			drops.add(this.getSeed());
		} else {
			int count = this.quantityDropped(meta, fortune, world.rand);
			for (int i = 0; i < count; i++) {
				ItemStack item = this.getItemstackDropped(meta, world.rand, fortune);
				if (item != null) {
					drops.add(item);
				}
			}
			for (int i = 0; i < 3 + fortune; ++i) {
				if (world.rand.nextInt(15) <= meta) {
					drops.add(this.getSeed());
				}
			}
		}
		return drops;
	}
}
