package ca.ntro.app;

import ca.ntro.app.services.EventService;
import ca.ntro.app.services.EventServiceNtro;
import ca.ntro.app.services.Window;
import ca.ntro.app.services.WindowNull;

public class NtroApp {

	/* <Window> */

	private static Window window = new WindowNull();

	static void registerWindow(Window window){
		NtroApp.window = window;
	}

	public static Window window(){
		return NtroApp.window;
	}

	/* </Window> */

	
	
	/* <Events> */

	private static EventService eventService = new EventServiceNtro();

	static void registerEventService(EventService eventService){
		NtroApp.eventService = eventService;
	}

	public static EventService events(){
		return NtroApp.eventService;
	}

	/* </Window> */
	

}
