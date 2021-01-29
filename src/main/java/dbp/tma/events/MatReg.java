package dbp.tma.events;

import dbp.tma.api.events.MaterialRegistrationEvent;
import dbp.tma.api.material.Material;
import dbp.tma.api.material.MaterialRegister;

public class MatReg implements MaterialRegistrationEvent {
	public enum Materials {
		copper(new Material().addPart("ingot").addPart("plate").addPart("test")),
		iron(new Material().addPart("plate").addPart("ingot").addPart("dust").setColor(0xFF0000)),
		steel(new Material().addPart("plate").setPartSet("shiny"));
		
		public final Material material;
		Materials(Material material){
			this.material = material;
			this.material.setName(this.toString());
		}
	}

	@Override
	public void event() {
		for (Materials material : Materials.values()) {
			MaterialRegister.registerMaterial(material.material);
		}
		
		/*for (Integer i = 0; i < 20000; i++) {
			Material test = new Material();
			for (Integer i2 = 0; i2 < 15; i2++) {
				test.addPart(i2.toString());
			}
			if(i == 1 |i == 19999)
				System.out.println(i);
			MaterialRegister.registerMaterial(new Material().setName(i.toString()).addPart("plate").addPart("ingot").addPart("dust"));
		}*/
	}
}
