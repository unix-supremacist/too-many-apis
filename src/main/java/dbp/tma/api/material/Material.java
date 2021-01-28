package dbp.tma.api.material;

import java.util.HashMap;

import dbp.tma.api.Main;

public class Material {
	int id;
	int color = 0xFFFFFF;
	String name;
	String type;
	boolean enabled = true;
	HashMap<String, String> parts = new HashMap<String, String>();
	
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
}
