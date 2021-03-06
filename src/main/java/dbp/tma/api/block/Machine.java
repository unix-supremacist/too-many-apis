package dbp.tma.api.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dbp.tma.Tma;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class Machine extends BlockContainer {
	private boolean isOn;
	@SideOnly(Side.CLIENT) private IIcon icon;
	private final Random random = new Random();

	public Machine() {
		super(Material.iron);
	}

	public void onBlockAdded(World w, int x, int y, int z){
		super.onBlockAdded(w, x, y, z);
		if (!w.isRemote){
			Block block = w.getBlock(x, y, z - 1);
			Block block1 = w.getBlock(x, y, z + 1);
			Block block2 = w.getBlock(x - 1, y, z);
			Block block3 = w.getBlock(x + 1, y, z);
			byte b0 = 3;

			if (block.func_149730_j() && !block1.func_149730_j()) b0 = 3;
			if (block1.func_149730_j() && !block.func_149730_j()) b0 = 2;
			if (block2.func_149730_j() && !block3.func_149730_j()) b0 = 5;
			if (block3.func_149730_j() && !block2.func_149730_j()) b0 = 4;
			w.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int u, float u1, float u2, float u3){
		if (!w.isRemote){
			Tile tile = (Tile)w.getTileEntity(x, y, z);
			if (tile != null) p.openGui("tma", 0 , w, x, y, z);
		}
		return true;
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack i){
		int l = MathHelper.floor_double((double)(p.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) w.setBlockMetadataWithNotify(x, y, z, 2, 2);
		if (l == 1) w.setBlockMetadataWithNotify(x, y, z, 5, 2);
		if (l == 2) w.setBlockMetadataWithNotify(x, y, z, 3, 2);
		if (l == 3) w.setBlockMetadataWithNotify(x, y, z, 4, 2);

		if (i.hasDisplayName()) ((Tile)w.getTileEntity(x, y, z)).setInventoryName(i.getDisplayName());
	}

	public void breakBlock(World w, int x, int y, int z, Block b, int u){
		Tile tile = (Tile)w.getTileEntity(x, y, z);
		if (tile != null){
			for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1){
				ItemStack itemstack = tile.getStackInSlot(i1);
				if (itemstack != null){
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					float f2 = this.random.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0){
						int j1 = this.random.nextInt(21) + 10;
						if (j1 > itemstack.stackSize) j1 = itemstack.stackSize;
						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(w, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						if (itemstack.hasTagCompound())
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());

						float f3 = 0.05F;
						entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
						entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
					w.spawnEntityInWorld(entityitem);
					}
				}
			}
			w.func_147453_f(x, y, z, b);
		}
		super.breakBlock(w, x, y, z, b, u);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta){
		return new Tile();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
}
