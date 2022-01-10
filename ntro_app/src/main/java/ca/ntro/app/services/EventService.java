package ca.ntro.app.services;

import ca.ntro.app.frontend.events.Event;

public interface EventService {

	<E extends Event> E newEvent(Class<E> eventClass);

}
