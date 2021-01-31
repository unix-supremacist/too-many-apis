package dbp.tma;

import cpw.mods.fml.common.registry.GameRegistry;
import dbp.tma.api.item.ItemBase;
import net.minecraft.item.Item;

public class materialItems {
	public static final Item test = new ItemBase(Reference.MODID).setName("test").addItem("testtest", 54);

	public static void register() {
		GameRegistry.registerItem(test, "test");
	}
}