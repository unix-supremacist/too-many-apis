package dbp.tma.api.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dbp.tma.api.material.Part;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

import java.util.HashMap;

public class BlockPart extends Block {
	protected String name;
	protected final HashMap<Integer, String> mats = new HashMap<>();
	protected IIcon[] icon = new IIcon[255];
	protected String modid;
	protected boolean enabled = true;
	protected static int psid = 1;
	protected int matid = 1;
	protected final HashMap<Integer, Integer> colors = new HashMap<>();
	protected static final HashMap<String, Integer> partSets = new HashMap<>();
	protected static final HashMap<Integer, String> matSet = new HashMap<>();
	protected static final HashMap<String, HashMap<String, Integer>> settingsInt = new HashMap<>();
	protected static final HashMap<String, HashMap<String, String>> settingsString = new HashMap<>();


	protected BlockPart(String modid, Material p_i45394_1_) {
		super(p_i45394_1_);
		//setHasSubtypes(true);
		this.modid = modid;
	}

	public BlockPart disable() {
		enabled = false;
		return this;
	}

	public BlockPart enable() {
		enabled = true;
		return this;
	}

	public BlockPart setColor(int color) {
		colors.put(matid, color);
		return this;
	}

	public BlockPart setSettingsInt(HashMap<String, Integer> settings, String id) {
		settingsInt.put(id, settings);
		return this;
	}

	public BlockPart setSettingsString(HashMap<String, String> settings, String id) {
		settingsString.put(id, settings);
		return this;
	}

	public BlockPart setName(String name) {
		this.name = name;
		return this;
	}

	public BlockPart addPartSet(String name) {
		if (!name.equals("default")) {
			if (!partSets.containsKey(name)) {
				partSets.put(name, psid);
				psid++;
			}
			matSet.put(matid, name);
		}
		return this;
	}

	public BlockPart addMaterial(String name) {
		if(!mats.containsValue(name)){
			this.mats.put(matid, name);
			matid++;
		}
		return this;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor(){
		//if (colors.containsKey(itemStack.getItemDamage())) {
		//	return colors.get(itemStack.getItemDamage());
		//}
		return 0xFFFFFF;
	}
}
