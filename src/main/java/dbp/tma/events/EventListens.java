package dbp.tma.events;

import dbp.tma.api.events.Event;

public class EventListens {
    public static void listen() {
        Event.listen(Event.PartRegistration, new PartReg());
        Event.listen(Event.MaterialRegistration, new MatReg());
    }
}
