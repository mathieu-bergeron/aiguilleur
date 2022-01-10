package ca.ntro.app.services;

import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.core.initialization.Factory;

public class EventServiceNtro implements EventService {

	@Override
	public <E extends Event> E newEvent(Class<E> eventClass) {
		E event = Factory.newInstance(eventClass);
		
		((EventNtro) event).setEventService(this);

		return event;
	}

	public <E extends Event> void triggerEvent(E eventNtro) {

		
	}

}
