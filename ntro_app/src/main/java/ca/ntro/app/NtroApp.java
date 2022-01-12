package ca.ntro.app;

import ca.ntro.app.services.EventService;
import ca.ntro.app.services.EventServiceNtro;
import ca.ntro.app.services.LocaleService;
import ca.ntro.app.services.LocaleServiceNull;
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
	
	
	

	/* <Locale> */

	private static LocaleService localeService = new LocaleServiceNull();

	static void registerLocaleService(LocaleService localeService){
		NtroApp.localeService = localeService;
	}

	public static Locale currentLocale() {
		return NtroApp.localeService.currentLocale();
	}

	public static Locale locale(String language){
		return NtroApp.localeService.newLocale(language);
	}

	public static Locale locale(String language, String country){
		return NtroApp.localeService.newLocale(language, country);
	}

	public static Locale locale(String language, String country, String variant){
		return NtroApp.localeService.newLocale(language, country, variant);
	}

	/* </Locale> */

	
	
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
