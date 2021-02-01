package dbp.tma;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dbp.tma.api.block.BaseContainer;
import dbp.tma.api.block.CropBase;
import dbp.tma.api.block.TileBase;
import dbp.tma.api.events.Event;
import dbp.tma.api.item.MetaFoodBase;
import dbp.tma.api.item.MetaSeedBase;
import dbp.tma.api.material.Registery;
import dbp.tma.events.EventListens;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Tma {
	CropBase testCrop = new CropBase(Reference.MODID).setName("cotton").setName("testCrop");
	Item test = new MetaSeedBase(testCrop).addItem("testSeed", 3).addCrop(testCrop, 3).setUnlocalizedName("test");
	Block chestTest = new BaseContainer(0).setBlockName("chestTest");
	Item testMetaFood = new MetaFoodBase(false).setName("metafood").addHeal(4, 4).addSat(4.0F, 4).addItem("cotton", 4).setModid(Reference.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		EventListens.listen();
		Event.runEvents();
		GameRegistry.registerItem(test, "test");
		GameRegistry.registerItem(testMetaFood, "metafood");
		testCrop.setItem(testMetaFood).setSeed(test).setItemMeta(4);
		GameRegistry.registerBlock(testCrop, "testCrop");
		GameRegistry.registerTileEntity(TileBase.class, "DbpTileBase");
		GameRegistry.registerBlock(chestTest, "chestTest");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Registery.registerOreDict();
	}
}