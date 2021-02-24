package dbp.tma.api.item.instance;

public class FoodInstance extends ItemInstance {
	protected final int hunger;
	protected final float saturation;

	public FoodInstance(String name, int healing, float saturation) {
		super(name);
		this.hunger = healing;
		this.saturation = saturation;
	}

	public int getHunger() {
		return this.hunger;
	}

	public float getSaturation() {
		return this.saturation;
	}
}
