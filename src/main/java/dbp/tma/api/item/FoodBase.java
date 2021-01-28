package dbp.tma.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood{
	public FoodBase(int heal, float sat) {
		super(heal, sat, false);
	}
	
	public FoodBase(int heal, float sat, boolean wolf) {
		super(heal, sat, wolf);
	}
	
	public FoodBase(int heal, boolean wolf) {
		super(heal, 0.0f, wolf);
	}
}
