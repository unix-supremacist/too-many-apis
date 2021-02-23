package dbp.tma.api.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import dbp.tma.api.block.Tile;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class handler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x,y,z);
		Container container = null;
		if (tile instanceof Tile) container = ((Tile) tile).getContainer(player.inventory);
		return container;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x,y,z);
		Gui gui = null;
		if (tile instanceof Tile) gui = ((Tile) tile).getGui(player.inventory);
		return gui;
	}
}
