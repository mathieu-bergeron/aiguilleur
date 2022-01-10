package ca.ntro.app.frontend;

import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.frontend.events.EventRegistrar;

public class EventRegistrarNtro implements EventRegistrar {

	@Override
	public <E extends Event> void registerEvent(Class<E> eventClass) {
		
	}

}
