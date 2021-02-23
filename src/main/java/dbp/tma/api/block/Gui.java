package dbp.tma.api.block;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class Gui extends GuiContainer {
	private Tile tile;
	private ResourceLocation guiTextures = new ResourceLocation("textures/gui/container/container.png");

	public Gui(InventoryPlayer player, Tile tile){
		super(new Container(player, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		this.mc.getTextureManager().bindTexture(guiTextures);
	}
}
