package dbp.tma.api.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class MetaSeedBase extends SeedBase{
	Block crop;
	static HashMap<Integer, String> items = new HashMap<Integer, String>();
	static HashMap<Integer, Block> crops = new HashMap<Integer, Block>();
	
	public MetaSeedBase(Block crop, Block farmland) {
		super(crop, farmland);
		this.crop = crop;
		setHasSubtypes(true);
	}
	
	public MetaSeedBase(Block crop) {
		super(crop, Blocks.farmland);
		this.crop = crop;
		setHasSubtypes(true);
	}
	
	public  MetaSeedBase addItem (String name, int id) {
		items.put(id, name);
		return this;
	}
	
	public  MetaSeedBase addCrop (Block crop, int id) {
		crops.put(id, crop);
		return this;
	}
	
	@Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3){
		if(crops.containsKey(this.getDamage(item)))
			return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, crops.get(this.getDamage(item)));
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, crop);
    }
}
