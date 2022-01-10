package ca.ntro.app.frontend.events;

import ca.ntro.app.services.EventServiceNtro;

public abstract class EventNtro 

       implements     Event {
	
	private EventServiceNtro eventService;

	public EventServiceNtro getEventService() {
		return eventService;
	}

	public void setEventService(EventServiceNtro eventService) {
		this.eventService = eventService;
	}
	
	
	@Override
	public void trigger() {
		getEventService().triggerEvent(this);
	}
	


}
