package dbp.tma.api.material;

import dbp.tma.api.item.ItemBase;

public class Part extends ItemBase{
	String partName;
	boolean enabled = true;
	
	public Part disable() {
		enabled = false;
		return this;
	}
	
	public Part enable() {
		enabled = true;
		return this;
	}
	
	public Part setPartName(String name) {
		this.partName = name;
		this.setName(name);
		return this;
	}
	
	public String getPartName() {
		return partName;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
