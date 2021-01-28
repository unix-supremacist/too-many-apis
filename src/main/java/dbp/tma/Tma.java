package dbp.tma;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dbp.tma.api.block.BaseContainer;
import dbp.tma.api.block.CropBase;
import dbp.tma.api.block.TileBase;
import dbp.tma.api.events.Event;
import dbp.tma.api.events.MaterialModifcationEvent;
import dbp.tma.api.events.MaterialRegistrationEvent;
import dbp.tma.api.events.PartModifcationEvent;
import dbp.tma.api.events.PartRegistrationEvent;
import dbp.tma.api.item.FoodBase;
import dbp.tma.api.item.MetaFoodBase;
import dbp.tma.api.item.MetaSeedBase;
import dbp.tma.api.item.SeedFoodBase;
import dbp.tma.api.material.Material;
import dbp.tma.api.material.MaterialRegister;
import dbp.tma.api.material.Part;
import dbp.tma.events.EventListens;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import dbp.tma.Reference;

@Mod(modid=Reference.MODID, name=Reference.NAME)


public class Tma {
	Block testCrop = new CropBase().setName("cotton").setIconLength(6).setBlockName("testCrop");
	Block testNigger = new CropBase().setName("nigger").setIconLength(6).setBlockName("testNigger");
	Item test = new MetaSeedBase(testCrop).addItem("testSeed", 3).addCrop(testNigger, 3).setUnlocalizedName("test");
	Block chestTest = new BaseContainer(0).setBlockName("chestTest");
	Item testMetaFood = new MetaFoodBase(false).setName("metafood").addHeal(4,4).addSat(4.0F, 4).addItem("cotton", 4).setModid("rematerialized");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		EventListens.listen();
		Event.runEvents();
		//materialItems.register();
		GameRegistry.registerItem(test, "test");
		GameRegistry.registerItem(testMetaFood, "metafood");
		((CropBase) testCrop).setItem(testMetaFood).setSeed(test).setItemMeta(4);
		GameRegistry.registerBlock(testCrop, "testCrop");
		GameRegistry.registerBlock(testNigger, "testNigger");
		GameRegistry.registerTileEntity(TileBase.class, "DbpTileBase");
		GameRegistry.registerBlock(chestTest, "chestTest");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MaterialRegister.registerOreDict();
	}
}