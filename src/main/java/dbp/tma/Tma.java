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

		//this.loadCrops(); //uncomment to play with crops ;p
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Registery.registerOreDict();
		NetworkRegistry.INSTANCE.registerGuiHandler(tmaInstance, new handler());
	}

	public MetaSeedFoodItem metaSeedFoodItem;
	public CropBase specialCarrotCrop;
	public ItemStack specialCarrotItemStack;

	private void loadCrops() {
		//Create seed food meta item
		this.metaSeedFoodItem = new MetaSeedFoodItem(Reference.MODID, Blocks.farmland);
		//Registers it to the forge registry
		GameRegistry.registerItem(this.metaSeedFoodItem, "metaSeedFoodItem");
		//Creates a carrot crop
		this.specialCarrotCrop = new CropBase(Reference.MODID, "CarrotCrop", this.metaSeedFoodItem, 0);
		//Registers it to the forge registry
		GameRegistry.registerBlock(this.specialCarrotCrop, "CarrotCrop");
		//Creates the carrot food and seed, bound to the carrot crop
		this.specialCarrotItemStack = this.metaSeedFoodItem.addItem("Carrot", 0, this.specialCarrotCrop, 50, 50);
	}
}
