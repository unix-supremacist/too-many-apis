package dbp.tma.api.material;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Registery {
	public static final HashMap<String, Part> parts = new HashMap<>();
	public static final HashMap<String, Material> materials = new HashMap<>();

	public static void registerMaterial(Material material) {
		materials.put(material.getName(), material);
		for (String matPart : material.parts) {
			if (parts.containsKey(matPart)) {
				if (parts.get(matPart).isEnabled() && matPart.equals(parts.get(matPart).getName())) {
					parts.get(matPart)
						.setColor(material.getColor())
						.setSettingsString(material.getSettingsString(), material.getName())
						.setSettingsInt(material.getSettingsInt(), material.getName())
						.addPartSet(material.getPartSet())
						.addMaterial(material.getName());
				}
			}
		}
	}

	public static void registerPart(Part part) {
		parts.put(part.getName(), part);
	}

	public static void registerItems() {
		for (Map.Entry<String, Part> part : parts.entrySet()) {
			GameRegistry.registerItem(part.getValue(), part.getValue().getName());
		}
	}

	public static void registerOreDict() {
		for (Map.Entry<String, Material> material : materials.entrySet()) {
			for (String part : material.getValue().parts) {
				OreDictionary.registerOre(part + StringUtils.capitalize(material.getKey()), new ItemStack(parts.get(part), 1, material.getValue().getId()));
			}
		}
	}

	public static Item getItemFromPart(String part) {
		return parts.get(part);
	}

	public static ItemStack getItemStackFromMaterial(String material, String part, int count) {
		return new ItemStack(getItemFromPart(part), 1, materials.get(material).getId());
	}
}
