package ca.ntro.app.services;

import ca.ntro.app.frontend.events.Event;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;

public interface EventService {

	<E extends Event> E newEvent(Class<E> eventClass);

	<E extends Event> void registerEventHandler(Class<E> eventClass, AtomicTask eventHandler);

}
