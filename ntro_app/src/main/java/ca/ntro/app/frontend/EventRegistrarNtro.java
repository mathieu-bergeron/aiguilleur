package ca.ntro.app.frontend;

import ca.ntro.app.events.Event;
import ca.ntro.app.events.EventRegistrar;

public class EventRegistrarNtro implements EventRegistrar {

	@Override
	public <E extends Event> void registerEvent(Class<E> eventClass) {
		
	}

}
