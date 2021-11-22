package ca.ntro.app.frontend;

public interface ControllerRegistrar {

	void registerRootController(Class<? extends RootController> controllerClass);
	void registerController(Class<? extends Controller> controllerClass);

}
