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
	protected final ItemStack drop;
	protected final ItemStack seed;//TODO Create item stack that holds just Item and Meta
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons = new IIcon[8];

	public CropBase(String modid, String name, ItemStack drop, ItemStack seed) {
		this.modid = modid;
		this.name = name;
		this.drop = drop;
		this.seed = seed;
	}

	public CropBase(String modid, String name, ItemStack drop) {
		this(modid, name, drop, drop);
	}

	protected ItemStack getDrop() {
		return this.drop.copy();
	}

	protected ItemStack getSeed() {
		return this.seed.copy();
	}

	/**
	 * Get seed item
	 *
	 * @return seed Item
	 */
	@Override
	protected Item func_149866_i() {
		return this.getDrop().getItem();
	}

	/**
	 * Get drop item
	 *
	 * @return drop Item
	 */
	@Override
	protected Item func_149865_P() {
		return this.getSeed().getItem();
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

	@Override //TODO Streamline the RNG logic, similar to vanilla.
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();

		int count = this.quantityDropped(meta, fortune, world.rand);
		for (int i = 0; i < count; i++) {
			ItemStack item = this.getItemstackDropped(meta, world.rand, fortune);
			if (item != null) {
				drops.add(item);
			}
		}

		if (meta >= 7) {
			for (int i = 0; i < 3 + fortune; ++i) {
				if (world.rand.nextInt(15) <= meta) {
					drops.add(this.getSeed());
				}
			}
		}
		return drops;
	}
}
