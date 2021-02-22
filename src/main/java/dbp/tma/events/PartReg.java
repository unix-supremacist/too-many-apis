package dbp.tma.events;

import dbp.tma.api.events.PartRegistrationEvent;
import dbp.tma.api.material.Part;
import dbp.tma.api.material.Registery;

public class PartReg implements PartRegistrationEvent {
	public enum Parts {
		ingot(new Part()),
		dust(new Part()),
		plate(new Part()),
		gear(new Part()),
		gem(new Part()),
		lens(new Part()),
		stick(new Part());

		public final Part part;

		Parts(Part part) {
			this.part = part.setName(this.toString());
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
