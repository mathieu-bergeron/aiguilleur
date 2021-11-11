package ca.ntro.tmp;

public interface ControllerRegistrar {

	void registerRootController(Class<? extends NtroRootController> controllerClass);
	void registerSubController(Class<? extends NtroController<?>> controllerClass);

}
