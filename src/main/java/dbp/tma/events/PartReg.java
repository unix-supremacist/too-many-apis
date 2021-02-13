package dbp.tma.events;

import dbp.tma.Reference;
import dbp.tma.api.events.PartRegistrationEvent;
import dbp.tma.api.material.Part;
import dbp.tma.api.material.Registery;

public class PartReg implements PartRegistrationEvent {
	public enum Parts {
		ingot(new Part(Reference.MODID)),
		dust(new Part(Reference.MODID)),
		plate(new Part(Reference.MODID)),
		gear(new Part(Reference.MODID)),
		gem(new Part(Reference.MODID)),
		lens(new Part(Reference.MODID)),
		stick(new Part(Reference.MODID));

		public final Part part;

		Parts(Part part) {
			this.part = part;
			this.part.setName(this.toString());
		}
	}

	@Override
	public void event() {
		for (Parts part : Parts.values()) {
			Registery.registerPart(part.part);
		}

		//for (Integer i = 0; i < 1000; i++) {
		//	Registery.registerPart(new Part(Reference.MODID).setName(i.toString()));
		//}
	}
}
