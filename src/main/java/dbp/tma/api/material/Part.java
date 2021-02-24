package dbp.tma.api.material;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dbp.tma.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Part extends Item {
	protected String name;
	protected final HashMap<Integer, String> mats = new HashMap<>();
	protected IIcon[] icon = new IIcon[255];
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
		this.setHasSubtypes(true);
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
	public void registerIcons(IIconRegister register) {
		this.icon[0] = register.registerIcon(this.modid + ":item_" + this.name);
		for (Entry<String, Integer> partSet : this.partSets.entrySet()) {
			this.icon[partSet.getValue()] = register.registerIcon(this.modid + ":" + partSet.getKey() + "/item_" + this.name);
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
	public String getUnlocalizedName(ItemStack item) {
		return "item." + this.modid + this.mats.get(item.getItemDamage()) + "_" + this.name;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemStack, int u) {
		if (this.colors.containsKey(itemStack.getItemDamage())) {
			return this.colors.get(itemStack.getItemDamage());
		}
		return 0xFFFFFF;
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
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
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		for (int i = 1; i <= this.mats.size(); i++) {
			itemList.add(new ItemStack(this, 1, i));
		}
	}
}
