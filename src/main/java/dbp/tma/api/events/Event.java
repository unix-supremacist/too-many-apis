package dbp.tma.api.events;

import dbp.tma.api.material.MaterialRegister;

import java.util.ArrayList;
import java.util.List;

public class Event {
    public static List<PartRegistrationEvent> PartRegistration = new ArrayList<>();
    public static List<PartModifcationEvent> PartModifcation = new ArrayList<>();
    public static List<MaterialRegistrationEvent> MaterialRegistration = new ArrayList<>();
    public static List<MaterialModifcationEvent> MaterialModifcation = new ArrayList<>();

    public static void listen(List event, BasicEvent call) {
        event.add(call);
    }

    public static void runEvents() {
        for (PartRegistrationEvent PartReg : PartRegistration) PartReg.event();
        for (PartModifcationEvent PartMod : PartModifcation) PartMod.event();
        for (MaterialRegistrationEvent MatReg : MaterialRegistration) MatReg.event();
        for (MaterialModifcationEvent MatMod : MaterialModifcation) MatMod.event();
        MaterialRegister.registerItems();
    }

}
