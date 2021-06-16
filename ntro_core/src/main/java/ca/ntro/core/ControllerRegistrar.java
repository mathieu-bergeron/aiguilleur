package ca.ntro.core;

public interface ControllerRegistrar {

	void registerRootController(Class<? extends NtroRootController> controllerClass);
	void registerSubController(Class<? extends NtroController<?>> controllerClass);

}
