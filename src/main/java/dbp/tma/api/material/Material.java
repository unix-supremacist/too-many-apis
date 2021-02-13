package dbp.tma.api.material;

import dbp.tma.api.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Material {
	protected int id;
	protected int color;
	protected String name;
	protected String type;
	protected String partSet = "default";
	protected boolean enabled = true;
	protected final HashSet<String> parts = new HashSet<>();
	protected final HashMap<String, String> settingsString = new HashMap<>();
	protected final HashMap<String, Integer> settingsInt = new HashMap<>();

	public Material(int color) {
		Main.lastMaterialId++;
		this.id = Main.lastMaterialId;
		this.color = color;
	}

	public Material(int color, String partSet){
		this(color);
		this.partSet = partSet;
	}

	public Material(String partSet){
		this();
		this.partSet = partSet;
	}

	public Material(){
		this(0xFFFFFF);
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

	public Material addPart(String... part) {
		this.parts.addAll(Arrays.asList(part));
		return this;
	}

	public Material addParts(String[]... partArrays) {
		for (String[] parts: partArrays) this.parts.addAll(Arrays.asList(parts));
		return this;
	}

	public String getPartSet() {
		return partSet;
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
