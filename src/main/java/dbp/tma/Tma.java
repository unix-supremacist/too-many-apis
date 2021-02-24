package dbp.tma;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import dbp.tma.api.block.CropBase;
import dbp.tma.api.events.Event;
import dbp.tma.api.gui.handler;
import dbp.tma.api.item.meta.MetaSeedFoodItem;
import dbp.tma.api.material.Registery;
import dbp.tma.events.EventListens;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Tma {
	@Mod.Instance(Reference.MODID)
	public static Tma tmaInstance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		EventListens.listen();
		Event.runEvents();

		this.loadCrops();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Registery.registerOreDict();
		NetworkRegistry.INSTANCE.registerGuiHandler(tmaInstance, new handler());
	}

	public MetaSeedFoodItem metaSeedFoodItem;
	public CropBase specialCarrotCrop;

	private void loadCrops() {
		this.metaSeedFoodItem = new MetaSeedFoodItem(Reference.MODID, Blocks.farmland);
		GameRegistry.registerItem(this.metaSeedFoodItem, "metaSeedFoodItem");
		this.specialCarrotCrop = new CropBase(Reference.MODID, "SpecialCarrot", new ItemStack(this.metaSeedFoodItem, 1, 0));
		this.metaSeedFoodItem.addItem("YummyCarrot", this.specialCarrotCrop, 50, 50);
		GameRegistry.registerBlock(this.specialCarrotCrop, "DirtyCarrot");
	}
}
