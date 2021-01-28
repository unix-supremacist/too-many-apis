package dbp.tma.api.material;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dbp.tma.api.item.ItemBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Part extends ItemBase{
	String partName;
	boolean enabled = true;
	static HashMap<Integer, Integer> colors = new HashMap<Integer, Integer>();
	
	public Part(String modid) {
		super(modid);
	}
	
	public Part disable() {
		enabled = false;
		return this;
	}
	
	public Part enable() {
		enabled = true;
		return this;
	}
	
	public Part setColor(int color, int id) {
		colors.put(id, color);
		return this;
	}
	
	public Part setPartName(String name) {
		this.partName = name;
		this.setName(name);
		return this;
	}
	
	//temporarily hardcode modid until shit is fixed
	@Override
	public void registerIcons(IIconRegister register){
		icon[0] = register.registerIcon("tma"+":item_"+partName);
	}
	
	public String getPartName() {
		return partName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int u){
    	if(colors.containsKey(itemStack.getItemDamage())) {
    		return colors.get(itemStack.getItemDamage());
    	}
    	return 0xFFFFFF;
    }
    
	@Override
	public IIcon getIconFromDamage(int meta){
		return icon[0];
	}

}
