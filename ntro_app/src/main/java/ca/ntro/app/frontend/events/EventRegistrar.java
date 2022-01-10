package ca.ntro.app.frontend.events;

public interface EventRegistrar {
	
	<E extends Event> void registerEvent(Class<E> eventClass);

}
