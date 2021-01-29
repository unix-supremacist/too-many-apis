package dbp.tma.api.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MaterialRegister {
	static HashMap<String, Part> parts = new HashMap<String, Part>();
	static HashMap<String, Material> materials = new HashMap<String, Material>();
	
	public static void registerMaterial(Material material){
		materials.put(material.getName(), material);
		for (Map.Entry<String, String> matPart : material.parts.entrySet()) {
			if (parts.containsKey(matPart.getKey())) {
				if (parts.get(matPart.getValue()).isEnabled()) {
					if (matPart.getValue() == parts.get(matPart.getValue()).getPartName()) {
						parts.get(matPart.getValue())
						.setColor(material.getColor(), material.getId())
						.setSettingsString(material.getSettingsString(), material.getId())
						.addPartSet(material.getPartSet(), material.getId())
						.addItem(material.getName(), material.getId());
					}
				}
			}
		}
	}
	
	public static void registerPart(Part part) {
		parts.put(part.getPartName(), part);
	}
	
	public static void registerItems() {
		for (Map.Entry<String, Part> part : parts.entrySet()) {
			GameRegistry.registerItem(part.getValue(), part.getValue().getPartName());
		}
	}
	
	public static void registerOreDict() {
		for (Map.Entry<String, Material> material : materials.entrySet()) {
			for(Map.Entry<String, String> part : material.getValue().parts.entrySet()) {
				OreDictionary.registerOre(part.getKey()+StringUtils.capitalize(material.getKey()), new ItemStack(parts.get(part.getKey()), 1, material.getValue().getId()));
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