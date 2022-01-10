package ca.ntro.app.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.core.initialization.Factory;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;

public class EventServiceNtro implements EventService {
	
	private Map<String, Set<AtomicTask>> eventHandlers = new HashMap<>();

	@Override
	public <E extends Event> E newEvent(Class<E> eventClass) {
		E event = Factory.newInstance(eventClass);
		
		((EventNtro) event).setEventService(this);

		return event;
	}

	public <E extends Event> void triggerEvent(E event) {
		String handlerId = Ntro.reflectionService().simpleName(event.getClass());
		
		Set<AtomicTask> handlers = eventHandlers.get(handlerId);
		if(handlers != null) {
			for(AtomicTask handler : handlers) {
				handler.addResult(event);
			}
		}
	}

	@Override
	public <E extends Event> void registerEventHandler(Class<E> eventClass, AtomicTask handler) {
		// FIXME
		String handlerId = handler.id().toKey().toString();
		handlerId = handlerId.replace("event[", "");
		handlerId = handlerId.replace("]", "");

		Set<AtomicTask> handlers = eventHandlers.get(handlerId);
		if(handlers == null) {
			handlers = new HashSet<>();
			eventHandlers.put(handlerId, handlers);
		}

		handlers.add(handler);
	}

}
