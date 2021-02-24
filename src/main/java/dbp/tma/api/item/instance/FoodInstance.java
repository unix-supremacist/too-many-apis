package dbp.tma.api.item.instance;

public class FoodInstance extends ItemInstance{
	protected final int healing;
	protected final float saturation;

	public FoodInstance(String name, int healing, float saturation) {
		super(name);
		this.healing = healing;
		this.saturation = saturation;
	}

	public int getHealing() {
		return this.healing;
	}

	public float getSaturation() {
		return this.saturation;
	}
}
