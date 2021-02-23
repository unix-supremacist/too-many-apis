package dbp.tma.events;

import dbp.tma.api.events.MaterialRegistrationEvent;
import dbp.tma.api.material.Material;
import dbp.tma.api.material.Registery;

import static dbp.tma.events.PartReg.Parts.*;

public class MatReg implements MaterialRegistrationEvent {
	public enum Materials {
		copper(new Material(0xB4713D).addPart(dust, ingot)),
		iron(new Material(0xB8B8B8).addPart(dust)),
		gold(new Material(0xFFFF1E).addPart(dust, plate)),
		electrum(new Material(0xFFFF64).addPart(dust, plate, ingot)),
		aluminium(new Material(0x80C8F0).addPart(dust, plate, ingot)),
		chrome(new Material(0xF5CEE3).addPart(dust, plate, ingot)),
		ruby(new Material(0xFF6464).addPart(dust, gem, plate, lens)),
		redGarnet(new Material(0xC85050).addPart(dust, gem, plate, lens)),
		platinum(new Material(0xFFFFC8).addPart(dust, ingot)),
		enderium(new Material(0x5A9187).addPart(dust, plate, ingot)),
		tungsten(new Material(0x323232).addPart(dust, plate, ingot)),
		bronze(new Material(0xFFB400).addPart(dust, gear, stick, plate, ingot)),
		mixedMetal(new Material("mixedMetal").addPart(ingot)),
		iridiumAlloy(new Material("iridiumAlloy").addPart(ingot)),
		advancedAlloy(new Material(0x87876E).addPart(plate)),
		nikolite(new Material(0x32C8FF).addPart(dust, gem)),
		redstone(new Material(0xC80000).addPart(gem)),
		tin(new Material(0xDCDCDC).addPart(dust, ingot)),
		wroughtIron(new Material(0xDCEBEB).addPart(dust, plate, ingot, gear, stick)),
		titanium(new Material(0xAA8FDE).addPart(dust, gear, stick, plate, ingot)),
		tungstensteel(new Material(0x6464A0).addPart(dust, gear, stick, plate, ingot)),
		steel(new Material(0x808080).addPart(dust, gear, stick, plate, ingot));

		public final Material material;

		Materials(Material material) {
			this.material = material.setName(this.toString());
		}
	}

	@Override
	public void event() {
		for (Materials material : Materials.values()) {
			Registery.registerMaterial(material.material);
		}

		//for (int i = 0; i < 3000; i++) {
		//	Material test = new Material().setName(i+"").setColor(i*5592);
		//	for (int i2 = 0; i2 < 32; i2++) {
		//		test.addPart(i2+i/3+"");
		//	}
		//	test.setPartSet(i/12+"");
		//	Registery.registerMaterial(test);
		//}
	}
}
