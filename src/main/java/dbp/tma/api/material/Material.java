package dbp.tma.api.material;

import java.util.HashMap;

import dbp.tma.api.Main;

public class Material {
	int id;
	int color = 0xFFFFFF;
	String name;
	String type;
	String partSet = "default";
	boolean enabled = true;
	HashMap<String, String> parts = new HashMap<String, String>();
	HashMap<String, String> settingsString = new HashMap<String, String>();
	HashMap<String, Integer> settingsInt = new HashMap<String, Integer>();
	
	public Material () {
		Main.lastMaterialId++;
		id = Main.lastMaterialId;
	}

	public Material setDisabled() {
		enabled = false;
		return this;
	}
	
	public Material setEnabled() {
		enabled = true;
		return this;
	}
	
	public Material setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Material addPart(String part) {
		parts.put(part, part);
		return this;
	}
	
	public Material setPartSet(String partSet) {
		this.partSet = partSet;
		return this;
	}
	
	public String getPartSet() {
		return partSet;
	}
	
	public Material setColor(int color) {
		this.color = color;
		return this;
	}
	
	public HashMap getParts() {
		return parts;
	}
	
	public int getId() {
		return id;
	}
	
	
	public int getColor() {
		return color;
	}
	
	public HashMap getSettingsString() {
		return settingsString;
	}
	
	public HashMap getSettingsInt() {
		return settingsInt;
	}
	
	public Material setSetting(String name, String setting) {
		settingsString.put(name, setting);
		return this;
	}
	
	public Material setSetting(String name, int setting) {
		settingsInt.put(name, setting);
		return this;
	}
}
