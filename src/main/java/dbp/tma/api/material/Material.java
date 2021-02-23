package dbp.tma.api.material;

import java.util.*;
import java.util.stream.Collectors;

public class Material {
	protected static int lastMaterialId = 0;

	protected int id;
	protected int color;
	protected String name;
	protected String partSet;
	protected boolean enabled = true;
	protected final HashSet<String> parts = new HashSet<>();
	protected final HashMap<String, String> settingsString = new HashMap<>();
	protected final HashMap<String, Integer> settingsInt = new HashMap<>();

	public Material(int color, String partSet) {
		this.id = ++lastMaterialId;
		this.color = color;
		this.partSet = partSet;
	}

	public Material(int color) {
		this(color, "default");
	}

	public Material(String partSet) {
		this(0xFFFFFF, partSet);
	}

	public Material() {
		this(0xFFFFFF, "default");
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
		return this.name;
	}

	public Material addParts(String[]... partArrays) {
		for (String[] parts : partArrays) this.parts.addAll(Arrays.asList(parts));
		return this;
	}

	public Material addPart(String... parts) {
		return addParts(parts);
	}

	public Material addPart(Object... parts) {
		for (Object partEnum: parts) this.parts.add(partEnum.toString());
		return this;
	}

	public String getPartSet() {
		return this.partSet;
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
