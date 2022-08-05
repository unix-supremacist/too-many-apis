package dbp.tma.api.material;

import dbp.tma.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.TextureRegistry;
import net.minecraft.item.itemgroup.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Texture;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Part extends Item {
	protected String name;
	protected final HashMap<Integer, String> mats = new HashMap<>();
	protected Texture[] icon = new Texture[255];
	protected String modid;
	protected boolean enabled = true;
	protected int partSetID = 1;
	protected int matSetID = 1;
	protected final HashMap<Integer, Integer> colors = new HashMap<>();
	protected final HashMap<String, Integer> partSets = new HashMap<>();
	protected final HashMap<Integer, String> matSet = new HashMap<>();
	protected final HashMap<String, HashMap<String, Integer>> settingsInt = new HashMap<>();
	protected final HashMap<String, HashMap<String, String>> settingsString = new HashMap<>();

	public Part(String modid) {
		//this.setHasSubtypes(true);
		this.modid = modid;
	}

	public Part() {
		this(Reference.MODID);
	}

	public Part disable() {
		this.enabled = false;
		return this;
	}

	public Part enable() {
		this.enabled = true;
		return this;
	}

	public Part setColor(int color) {
		this.colors.put(this.matSetID, color);
		return this;
	}

	public Part setSettingsInt(HashMap<String, Integer> settings, String id) {
		this.settingsInt.put(id, settings);
		return this;
	}

	public Part setSettingsString(HashMap<String, String> settings, String id) {
		this.settingsString.put(id, settings);
		return this;
	}

	public Part setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public void method_5462(TextureRegistry register) {
		this.icon[0] = register.registerTexture(this.modid + ":item_" + this.name);
		for (Entry<String, Integer> partSet : this.partSets.entrySet()) {
			this.icon[partSet.getValue()] = register.registerTexture(this.modid + ":" + partSet.getKey() + "/item_" + this.name);
		}
	}

	public String getName() {
		return this.name;
	}

	public String getModid() {
		return this.modid;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String getItemstackTranslatedName(ItemStack item) {
		return "item." + this.modid + this.mats.get(item.getDamage()) + "_" + this.name;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public int getDisplayColor(ItemStack itemStack, int u) {
		if (this.colors.containsKey(itemStack.getDamage())) {
			return this.colors.get(itemStack.getDamage());
		}
		return 0xFFFFFF;
	}

	@Override
	public Texture method_3343(int meta) {
		if (this.matSet.containsKey(meta)) {
			return this.icon[this.partSets.get(this.matSet.get(meta))];
		}
		return this.icon[0];
	}

	public Part addPartSet(String name) {
		if (!name.equals("default")) {
			if (!this.partSets.containsKey(name)) {
				this.partSets.put(name, this.partSetID);
				this.partSetID++;
			}
			this.matSet.put(this.matSetID, name);
		}
		return this;
	}

	public Part addMaterial(String name) {
		if (!this.mats.containsValue(name)) {
			this.mats.put(this.matSetID, name);
			this.matSetID++;
		}
		return this;
	}

	@Override
	public void appendItemStacks(Item item, ItemGroup tabs, List itemList) {
		for (int i = 1; i <= this.mats.size(); i++) {
			itemList.add(new ItemStack(this, 1, i));
		}
	}
}
