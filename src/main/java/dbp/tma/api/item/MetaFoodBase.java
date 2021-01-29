package dbp.tma.api.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaFoodBase extends FoodBase{
	HashMap<Integer, Integer> HealAmounts = new HashMap<Integer, Integer>();
	HashMap<Integer, Float> SatAmounts = new HashMap<Integer, Float>();
	static HashMap<Integer, String> items = new HashMap<Integer, String>();
	public IIcon[] icon = new IIcon[255];
	String modid;
	String name;

	public MetaFoodBase(boolean wolf) {
		super(0, wolf);
		setHasSubtypes(true);
	}
	
	@Override
    public int func_150905_g(ItemStack item){
		if(HealAmounts.containsKey(item.getItemDamage())) {
			return HealAmounts.get(item.getItemDamage());
		}
        return 0;
    }
	
	@Override
    public float func_150906_h(ItemStack item){
		if(SatAmounts.containsKey(item.getItemDamage())) {
			return SatAmounts.get(item.getItemDamage());
		}
		return 0.0F;
    }
	
	@Override
	public String getUnlocalizedName(ItemStack item) {
		return "item."+items.get(item.getItemDamage())+name;
	}
	
	public MetaFoodBase setName (String name) {
		this.name = name;
		return this;
	}
	
	public MetaFoodBase setModid (String modid) {
		this.modid = modid;
		return this;
	}

	public MetaFoodBase addItem (String name, int id) {
		items.put(id, name);
		return this;
	}
	
	public MetaFoodBase addHeal (int heal, int id) {
		HealAmounts.put(id, heal);
		return this;
	}
	
	public MetaFoodBase addSat (float sat, int id) {
		SatAmounts.put(id, sat);
		return this;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList){
		for(Map.Entry<Integer, String> i : items.entrySet()){
			itemList.add(new ItemStack(this, 1, i.getKey()));
		}
	}

	@Override
	public void registerIcons(IIconRegister register){
		for(Map.Entry<Integer, String> item : items.entrySet()){
			icon[item.getKey()] = register.registerIcon(modid+":item_"+item.getValue());
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta){
		return icon[meta];
	}
	
}
