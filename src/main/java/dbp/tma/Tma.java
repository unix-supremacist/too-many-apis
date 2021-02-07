package dbp.tma;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dbp.tma.api.block.CropBase;
import dbp.tma.api.events.Event;
import dbp.tma.api.item.MetaSeedFoodBase;
import dbp.tma.api.material.Registery;
import dbp.tma.events.EventListens;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Tma {
	CropBase testCrop = new CropBase(Reference.MODID).setName("cotton").setName("testCrop");
	Item testSeedMetaFood = new MetaSeedFoodBase(Reference.MODID).addHeal(4, 4).addSat(4.0F, 4).addItem("cotton", 4).addCrop(testCrop, 4).setIconSize(5);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		EventListens.listen();
		Event.runEvents();
		GameRegistry.registerItem(testSeedMetaFood, "metafood");
		testCrop.setItem(testSeedMetaFood).setSeed(testSeedMetaFood).setItemMeta(4);
		GameRegistry.registerBlock(testCrop, "testCrop");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Registery.registerOreDict();
	}
}