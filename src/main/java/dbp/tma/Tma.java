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
import net.minecraft.item.Item;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Tma {
	@Mod.Instance(Reference.MODID)
	public static Tma tmaInstance;

	CropBase testCrop;
	Item testSeedMetaFood;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		EventListens.listen();
		Event.runEvents();

		//Creates a meta item that can store a ton of seed-food items
		this.testSeedMetaFood = new MetaSeedFoodItem(Reference.MODID, Blocks.farmland);
		GameRegistry.registerItem(this.testSeedMetaFood, "metafood");

		this.testCrop = new CropBase(Reference.MODID, "testCrop", this.testSeedMetaFood, 0, this.testSeedMetaFood, 0);
		((MetaSeedFoodItem)this.testSeedMetaFood).addItem("testCrop", this.testCrop, 0, 0);

		GameRegistry.registerBlock(this.testCrop, "testCrop");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Registery.registerOreDict();
		NetworkRegistry.INSTANCE.registerGuiHandler(tmaInstance, new handler());
	}
}
