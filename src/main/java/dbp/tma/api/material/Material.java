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
        parts.add(part);
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

    public HashSet getParts() {
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
