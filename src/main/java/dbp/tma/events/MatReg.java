package dbp.tma.events;

import dbp.tma.api.events.MaterialRegistrationEvent;
import dbp.tma.api.material.Material;
import dbp.tma.api.material.Registery;

public class MatReg implements MaterialRegistrationEvent {
	public enum Materials {
		copper(new Material().addPart("ingot").addPart("plate").addPart("test").setColor(0xFF6400)),
		iron(new Material().addPart("plate").addPart("ingot").addPart("dust").setColor(0x7F7F7F)),
		steel(new Material().addPart("plate").setPartSet("shiny").setColor(0xFF64FF));

		public final Material material;

		Materials(Material material) {
			this.material = material;
			this.material.setName(this.toString());
		}
	}

	@Override
	public void event() {
		for (Materials material : Materials.values()) {
			Registery.registerMaterial(material.material);
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
