package dbp.tma.api.material;

import dbp.tma.api.Main;

import java.util.HashMap;
import java.util.HashSet;

public class Material {
	protected int id;
	protected int color = 0xFFFFFF;
	protected String name;
	protected String type;
	protected String partSet = "default";
	protected boolean enabled = true;
	protected final HashSet<String> parts = new HashSet<>();
	protected final HashMap<String, String> settingsString = new HashMap<>();
	protected final HashMap<String, Integer> settingsInt = new HashMap<>();

	public Material() {
		Main.lastMaterialId++;
		this.id = Main.lastMaterialId;
	}

	public Material setDisabled() {
		this.enabled = false;
		return this;
	}

	public Material setEnabled() {
		this.enabled = true;
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
		this.parts.add(part);
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

	public HashSet<String> getParts() {
		return this.parts;
	}

	public int getId() {
		return this.id;
	}


	public int getColor() {
		return this.color;
	}

	public HashMap<String, String> getSettingsString() {
		return this.settingsString;
	}

	public HashMap<String, Integer> getSettingsInt() {
		return this.settingsInt;
	}

	public Material setSetting(String name, String setting) {
		this.settingsString.put(name, setting);
		return this;
	}

	public Material setSetting(String name, int setting) {
		this.settingsInt.put(name, setting);
		return this;
	}
}
