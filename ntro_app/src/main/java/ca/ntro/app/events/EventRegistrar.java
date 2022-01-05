package ca.ntro.app.events;

public interface EventRegistrar {
	
	<E extends Event> void registerEvent(Class<E> eventClass);

}
