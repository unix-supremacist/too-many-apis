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
	protected Item dropItem;
	protected Item seedItem;
	protected int itemMeta;
	protected int seedMeta;
	protected String modid;
	protected String name;
	@SideOnly(Side.CLIENT)
	protected IIcon[] cropTexture = new IIcon[8];

	public CropBase(String modid, String name, Item dropItem, int itemMeta, Item seedItem, int seedMeta) {
		this.modid = modid;
		this.name = name;
		this.dropItem = dropItem;
		this.itemMeta = itemMeta;
		this.seedItem = seedItem;
		this.seedMeta = seedMeta;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int growth) {
		return this.cropTexture[growth];
	}

	protected ItemStack getSeed() {
		return new ItemStack(this.seedItem, 1, this.seedMeta);
	}

	protected ItemStack getDrop() {
		return new ItemStack(this.dropItem, 1, this.itemMeta);
	}

	@Override
	protected Item func_149866_i() {
		return this.seedItem;
	}

	@Override
	protected Item func_149865_P() {
		return this.dropItem;
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + this.modid + "." + this.name;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		for (int i = 0; i < this.cropTexture.length; ++i) {
			this.cropTexture[i] = icon.registerIcon(this.modid + ":crop_" + this.name + "_" + i);
		}
	}

	public ItemStack getItemstackDropped(int meta, Random random, int fortune) {
		return meta == 7 ? this.getDrop() : this.getSeed();
	}

	@Override
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
