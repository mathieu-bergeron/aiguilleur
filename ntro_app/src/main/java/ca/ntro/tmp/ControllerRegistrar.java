package ca.ntro.tmp;

import ca.ntro.app.frontend.NtroController;
import ca.ntro.app.frontend.NtroRootController;

public interface ControllerRegistrar {

	void registerRootController(Class<? extends NtroRootController> controllerClass);
	void registerSubController(Class<? extends NtroController<?>> controllerClass);

}
