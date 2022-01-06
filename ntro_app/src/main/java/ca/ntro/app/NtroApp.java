package ca.ntro.app;

import ca.ntro.app.frontend.Window;
import ca.ntro.app.frontend.WindowNull;

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

}
