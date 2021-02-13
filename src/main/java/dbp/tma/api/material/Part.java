package dbp.tma.api.material;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
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
	protected int psid = 1;
	protected int matid = 1;
	protected final HashMap<Integer, Integer> colors = new HashMap<>();
	protected final HashMap<String, Integer> partSets = new HashMap<>();
	protected final HashMap<Integer, String> matSet = new HashMap<>();
	protected final HashMap<String, HashMap<String, Integer>> settingsInt = new HashMap<>();
	protected final HashMap<String, HashMap<String, String>> settingsString = new HashMap<>();

	public Part(String modid) {
		setHasSubtypes(true);
		this.modid = modid;
	}

	public Part disable() {
		enabled = false;
		return this;
	}

	public Part enable() {
		enabled = true;
		return this;
	}

	public Part setColor(int color) {
		colors.put(matid, color);
		return this;
	}

	public Part setSettingsInt(HashMap<String, Integer> settings, String id) {
		settingsInt.put(id, settings);
		return this;
	}

	public Part setSettingsString(HashMap<String, String> settings, String id) {
		settingsString.put(id, settings);
		return this;
	}

	public Part setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		icon[0] = register.registerIcon("tma" + ":item_" + name);
		for (Entry<String, Integer> partSet : partSets.entrySet()) {
			icon[partSet.getValue()] = register.registerIcon(modid + ":" + partSet.getKey() + "/item_" + name);
		}
	}

	public String getName() {
		return name;
	}

	public String getModid (){
		return modid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return "material." +mats.get(item.getItemDamage()) + "_" + this.name;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemStack, int u) {
		if (colors.containsKey(itemStack.getItemDamage())) {
			return colors.get(itemStack.getItemDamage());
		}
		return 0xFFFFFF;
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (matSet.containsKey(meta)) {
			return icon[partSets.get(matSet.get(meta))];
		}
		return icon[0];
	}

	public Part addPartSet(String name) {
		if (!name.equals("default")) {
			if (!partSets.containsKey(name)) {
				partSets.put(name, psid);
				psid++;
			}
			matSet.put(matid, name);
		}
		return this;
	}

	public Part addMaterial(String name) {
		if(!mats.containsValue(name)){
			this.mats.put(matid, name);
			matid++;
		}
		return this;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		for (int i = 1; i <= mats.size(); i++) {
			itemList.add(new ItemStack(this, 1, i));
		}
	}
}